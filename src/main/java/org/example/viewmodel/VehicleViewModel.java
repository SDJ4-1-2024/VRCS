package org.example.viewmodel;

import javafx.beans.property.*;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleType;

public class VehicleViewModel {

    private StringProperty make;
    private StringProperty brand;
    private StringProperty registrationPlate;
    private ObjectProperty<VehicleType> vehicleType;
    private IntegerProperty pricePerDay;

    public VehicleViewModel(Vehicle vehicle) {
        this.make = new SimpleStringProperty(vehicle.getMake());
        this.brand = new SimpleStringProperty(vehicle.getBrand());
        this.registrationPlate = new SimpleStringProperty(vehicle.getRegistrationPlate());
        this.vehicleType = new SimpleObjectProperty<>(vehicle.getVehicleType());
        this.pricePerDay = new SimpleIntegerProperty(vehicle.getPricePerDay());
    }

    public StringProperty makeProperty() {
        return make;
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public StringProperty registrationPlateProperty() {
        return registrationPlate;
    }

    public ObjectProperty<VehicleType> vehicleTypeProperty() {
        return vehicleType;
    }

    public IntegerProperty pricePerDayProperty() {
        return pricePerDay;
    }
}
