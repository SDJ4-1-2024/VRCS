package org.example.viewmodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import org.example.model.vehicle.Vehicle;

public class ManageVehicleViewModel {

    private final StringProperty make;
    private final StringProperty brand;
    private final StringProperty registrationPlate;
    private final StringProperty vehicleType;
    private final IntegerProperty pricePerDay;

    public ManageVehicleViewModel(Vehicle vehicle) {
        this.make = new SimpleStringProperty(vehicle.getMake());
        this.brand = new SimpleStringProperty(vehicle.getBrand());
        this.registrationPlate = new SimpleStringProperty(vehicle.getRegistrationPlate());
        this.vehicleType = new SimpleStringProperty(vehicle.getVehicleType().toString());
        this.pricePerDay = new SimpleIntegerProperty(vehicle.getPricePerDay());
    }

    public String getMake() {
        return make.get();
    }

    public StringProperty makeProperty() {
        return make;
    }

    public String getBrand() {
        return brand.get();
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public String getRegistrationPlate() {
        return registrationPlate.get();
    }

    public StringProperty registrationPlateProperty() {
        return registrationPlate;
    }

    public String getVehicleType() {
        return vehicleType.get();
    }

    public StringProperty vehicleTypeProperty() {
        return vehicleType;
    }

    public Integer getPricePerDay() {
        return pricePerDay.get();
    }

    public IntegerProperty pricePerDayProperty() {
        return pricePerDay;
    }
}
