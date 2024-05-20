package org.example.viewmodel.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientViewModel {

    private ObservableList<String> availableVehicles = FXCollections.observableArrayList();
    private ObservableList<String> bookings = FXCollections.observableArrayList();

    public ClientViewModel() {
        // Initialize with some data
        availableVehicles.addAll("Car 1", "Car 2", "Van 1", "Trailer 1");
    }

    public ObservableList<String> getAvailableVehicles() {
        return availableVehicles;
    }

    public ObservableList<String> getBookings() {
        return bookings;
    }

    public void rentVehicle(String vehicle) {
        // Handle vehicle renting logic
        bookings.add(vehicle);
        availableVehicles.remove(vehicle);
        // Notify server
        // ...
    }
}
