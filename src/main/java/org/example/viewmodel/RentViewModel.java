package org.example.viewmodel;

import javafx.beans.property.*;
import org.example.model.Rent;
import org.example.model.client.Client;
import org.example.model.vehicle.Vehicle;

import java.time.LocalDate;

public class RentViewModel {
    private ObjectProperty<LocalDate> startDate;
    private ObjectProperty<LocalDate> endDate;
    private ObjectProperty<Vehicle> vehicle;
    private ObjectProperty<Client> client;
    private BooleanProperty ongoing;

    public RentViewModel(Rent rent) {
        this.startDate = new SimpleObjectProperty<>(rent.getStartDate());
        this.endDate = new SimpleObjectProperty<>(rent.getEndDate());
        this.vehicle = new SimpleObjectProperty<>(rent.getVehicle());
        this.client = new SimpleObjectProperty<>(rent.getClient());
        this.ongoing = new SimpleBooleanProperty(rent.isOngoing());
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public ObjectProperty<Vehicle> vehicleProperty() {
        return vehicle;
    }

    public ObjectProperty<Client> clientProperty() {
        return client;
    }

    public BooleanProperty ongoingProperty() {
        return ongoing;
    }
}
