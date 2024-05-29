package org.example.viewmodel.vehicle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import org.example.model.vehicle.Trailer;

public class TrailerViewModel {

    private final StringProperty make;
    private final StringProperty brand;
    private final StringProperty registrationPlate;
    private final IntegerProperty trunkSpaceHeight;
    private final IntegerProperty trunkSpaceWidth;
    private final IntegerProperty carryingCapacity;
    private final IntegerProperty pricePerDay;

    public TrailerViewModel(Trailer trailer) {
        this.make = new SimpleStringProperty(trailer.getMake());
        this.brand = new SimpleStringProperty(trailer.getBrand());
        this.registrationPlate = new SimpleStringProperty(trailer.getRegistrationPlate());
        this.trunkSpaceHeight = new SimpleIntegerProperty(trailer.getTrunkSpaceHeight());
        this.trunkSpaceWidth = new SimpleIntegerProperty(trailer.getTrunkSpaceWidth());
        this.carryingCapacity = new SimpleIntegerProperty(trailer.getCarryingCapacity());
        this.pricePerDay = new SimpleIntegerProperty(trailer.getPricePerDay());
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

    public int getPricePerDay() {
        return pricePerDay.get();
    }

    public IntegerProperty pricePerDayProperty() {
        return pricePerDay;
    }
}
