package org.example.viewmodel.vehicle;

import javafx.beans.property.*;
import org.example.model.vehicle.Car;
import org.example.model.vehicle.VehicleType;

public class CarViewModel {

    private StringProperty make;
    private StringProperty brand;
    private StringProperty registrationPlate;
    private ObjectProperty<VehicleType> vehicleType;
    private IntegerProperty pricePerDay;
    private IntegerProperty numberOfSeats;
    private IntegerProperty trunkCapacity;
    private IntegerProperty hp;

    public CarViewModel(Car car) {
        this.make = new SimpleStringProperty(car.getMake());
        this.brand = new SimpleStringProperty(car.getBrand());
        this.registrationPlate = new SimpleStringProperty(car.getRegistrationPlate());
        this.vehicleType = new SimpleObjectProperty<>(car.getVehicleType());
        this.pricePerDay = new SimpleIntegerProperty(car.getPricePerDay());
        this.numberOfSeats = new SimpleIntegerProperty(car.getNumberOfSeats());
        this.trunkCapacity = new SimpleIntegerProperty(car.getTrunkCapacity());
        this.hp = new SimpleIntegerProperty(car.getHp());
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
        return vehicleType.get().name();
    }

    public ObjectProperty<VehicleType> vehicleTypeProperty() {
        return vehicleType;
    }

    public int getPricePerDay() {
        return pricePerDay.get();
    }

    public IntegerProperty pricePerDayProperty() {
        return pricePerDay;
    }

    public int getNumberOfSeats() {
        return numberOfSeats.get();
    }

    public IntegerProperty numberOfSeatsProperty() {
        return numberOfSeats;
    }

    public int getTrunkCapacity() {
        return trunkCapacity.get();
    }

    public IntegerProperty trunkCapacityProperty() {
        return trunkCapacity;
    }

    public int getHp() {
        return hp.get();
    }

    public IntegerProperty hpProperty() {
        return hp;
    }
}
