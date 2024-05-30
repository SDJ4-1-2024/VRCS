package org.example.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.example.model.Booking;

public class BookingViewModel {

    private final StringProperty clientPhoneNumber;
    private final StringProperty vehicleRegistrationPlate;
    private final StringProperty vehicleType;
    private final StringProperty startDate;
    private final StringProperty endDate;

    public BookingViewModel(Booking booking) {
        this.clientPhoneNumber = new SimpleStringProperty(booking.getClient().phoneNumber());
        this.vehicleRegistrationPlate = new SimpleStringProperty(booking.getVehicle().getRegistrationPlate());
        this.vehicleType = new SimpleStringProperty(booking.getVehicle().getVehicleType().toString());
        this.startDate = new SimpleStringProperty(booking.getStartDate().toString());
        this.endDate = new SimpleStringProperty(booking.getEndDate().toString());
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber.get();
    }

    public StringProperty clientPhoneNumberProperty() {
        return clientPhoneNumber;
    }

    public String getVehicleRegistrationPlate() {
        return vehicleRegistrationPlate.get();
    }

    public StringProperty vehicleRegistrationPlateProperty() {
        return vehicleRegistrationPlate;
    }

    public String getVehicleType() {
        return vehicleType.get();
    }

    public StringProperty vehicleTypeProperty() {
        return vehicleType;
    }

    public String getStartDate() {
        return startDate.get();
    }

    public StringProperty startDateProperty() {
        return startDate;
    }

    public String getEndDate() {
        return endDate.get();
    }

    public StringProperty endDateProperty() {
        return endDate;
    }
}
