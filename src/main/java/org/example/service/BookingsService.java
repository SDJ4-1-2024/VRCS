package org.example.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Booking;
import org.example.repository.BookingsRepository;
import org.example.repository.ClientRepository;
import org.example.repository.vehicle.VehicleRepository;
import org.example.util.ClientUtils;
import org.example.util.LocalDateConverter;
import org.example.util.PopUpUtil;
import org.example.util.logger.SingletonLog;
import org.example.viewmodel.BookingViewModel;

import java.time.LocalDate;
import java.util.List;

public class BookingsService {

    private final BookingsRepository bookingsRepository;

    public BookingsService() {
        bookingsRepository = new BookingsRepository();
    }

    public ObservableList<BookingViewModel> prepareBookings() {
        List<Booking> bookings;
        if (ClientUtils.isAdmin()) {
            bookings = bookingsRepository.loadBookings();
        } else {
            ClientRepository clientRepository = new ClientRepository();
            Integer clientId = clientRepository.prepareClientIdByPhoneNumber(ClientUtils.getPhoneNumber()).orElseThrow();
            bookings = bookingsRepository.loadBookingsByClientId(clientId);
        }
        ObservableList<BookingViewModel> bookingData = FXCollections.observableArrayList();
        for (Booking booking : bookings) {
            bookingData.add(new BookingViewModel(booking));
        }
        return bookingData;
    }

    public boolean isRemoved(BookingViewModel selectedBooking) {
        if (selectedBooking != null) {
            String startDate = selectedBooking.getStartDate();
            String endDate = selectedBooking.getEndDate();
            LocalDate startDateMod = LocalDate.parse(startDate);
            LocalDate endDateMod = LocalDate.parse(endDate);
            if (!isOngoing(startDateMod, endDateMod)) {
                Integer vehicleId = prepareVehicleId(selectedBooking);
                Integer clientId = prepareClientId(selectedBooking);
                bookingsRepository.removeBooking(LocalDateConverter.convertToDatabaseColumn(startDateMod), LocalDateConverter.convertToDatabaseColumn(endDateMod), vehicleId, clientId);
                SingletonLog.getInstance().addLog("Booking of: vehicleId: "+vehicleId +" and clientId: "+clientId+" has been removed");
                return true;
            } else {
                PopUpUtil.popUpInfo("Booking is ongoing", "You cannot remove currently ongoing booking");
                SingletonLog.getInstance().addLog("Booking has NOT been removed");
                return false;
            }

        }
        return false;
    }

    private static Integer prepareClientId(BookingViewModel selectedBooking) {
        String clientPhoneNumber = selectedBooking.getClientPhoneNumber();
        ClientRepository clientRepository = new ClientRepository();
        return clientRepository.prepareClientIdByPhoneNumber(clientPhoneNumber).orElseThrow();
    }

    private static Integer prepareVehicleId(BookingViewModel selectedBooking) {
        String vehicleRegistrationPlate = selectedBooking.getVehicleRegistrationPlate();
        VehicleRepository vehicleRepository = new VehicleRepository();
        return vehicleRepository.prepareVehicleIdByRegPlate(vehicleRegistrationPlate).orElseThrow();
    }

    private boolean isOngoing(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();
        return (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)) || currentDate.equals(startDate) || currentDate.equals(endDate);
    }

}
