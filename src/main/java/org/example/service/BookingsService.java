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
import org.example.viewmodel.BookingViewModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookingsService {

    private static BookingsRepository bookingsRepository;

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
            BookingsService bookingsService = new BookingsService();
            LocalDate startDateMod = LocalDate.parse(startDate);
            LocalDate endDateMod = LocalDate.parse(endDate);
            if (!bookingsService.isOngoing(startDateMod, endDateMod)){
                String vehicleRegistrationPlate = selectedBooking.getVehicleRegistrationPlate();
                VehicleRepository vehicleRepository = new VehicleRepository();
                Integer vehicleId = vehicleRepository.prepareVehicleIdByRegPlate(vehicleRegistrationPlate).orElseThrow();
                String clientPhoneNumber = selectedBooking.getClientPhoneNumber();
                ClientRepository clientRepository = new ClientRepository();
                Integer clientId = clientRepository.prepareClientIdByPhoneNumber(clientPhoneNumber).orElseThrow();
                bookingsRepository.removeBooking(LocalDateConverter.convertToDatabaseColumn(startDateMod), LocalDateConverter.convertToDatabaseColumn(endDateMod), vehicleId, clientId);
                return true;
            }
            else {
                PopUpUtil.popUpInfo("Booking is ongoing", "You cannot remove currently ongoing booking");
                return false;
            }

        }
        return false;
    }

    private boolean isOngoing(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();
        return (currentDate.isAfter(startDate)  && currentDate.isBefore(endDate)) || currentDate.equals(startDate)  || currentDate.equals(endDate);
    }

}
