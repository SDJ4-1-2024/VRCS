package org.example.viewmodel.vehicle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import org.example.model.vehicle.Van;

public class VanViewModel {

    private final StringProperty make;
    private final StringProperty brand;
    private final StringProperty registrationPlate;
    private final IntegerProperty trunkSpaceHeight;
    private final IntegerProperty trunkSpaceWidth;
    private final IntegerProperty carryingCapacity;
    private final IntegerProperty hp;
    private final IntegerProperty pricePerDay;

    public VanViewModel(Van van) {
        this.make = new SimpleStringProperty(van.getMake());
        this.brand = new SimpleStringProperty(van.getBrand());
        this.registrationPlate = new SimpleStringProperty(van.getRegistrationPlate());
        this.trunkSpaceHeight = new SimpleIntegerProperty(van.getTrunkSpaceHeight());
        this.trunkSpaceWidth = new SimpleIntegerProperty(van.getTrunkSpaceWidth());
        this.carryingCapacity = new SimpleIntegerProperty(van.getCarryingCapacity());
        this.hp = new SimpleIntegerProperty(van.getHp());
        this.pricePerDay = new SimpleIntegerProperty(van.getPricePerDay());
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

    public int getTrunkSpaceHeight() {
        return trunkSpaceHeight.get();
    }

    public IntegerProperty trunkSpaceHeightProperty() {
        return trunkSpaceHeight;
    }

    public int getTrunkSpaceWidth() {
        return trunkSpaceWidth.get();
    }

    public IntegerProperty trunkSpaceWidthProperty() {
        return trunkSpaceWidth;
    }

    public int getCarryingCapacity() {
        return carryingCapacity.get();
    }

    public IntegerProperty carryingCapacityProperty() {
        return carryingCapacity;
    }

    public int getHp() {
        return hp.get();
    }

    public IntegerProperty hpProperty() {
        return hp;
    }

    public int getPricePerDay() {
        return pricePerDay.get();
    }

    public IntegerProperty pricePerDayProperty() {
        return pricePerDay;
    }
}
