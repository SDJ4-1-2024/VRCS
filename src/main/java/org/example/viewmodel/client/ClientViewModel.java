package org.example.viewmodel.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientViewModel {

    private ObservableList<String> availableVehicles = FXCollections.observableArrayList();
    private ObservableList<String> bookings = FXCollections.observableArrayList();

    public ClientViewModel() {
        
        availableVehicles.addAll("Car 1", "Car 2", "Van 1", "Trailer 1");
    }

    public ObservableList<String> getAvailableVehicles() {
        return availableVehicles;
    }

    public ObservableList<String> getBookings() {
        return bookings;
    }

    public void rentVehicle(String vehicle) {
        
        bookings.add(vehicle);
        availableVehicles.remove(vehicle);
        
        
    }
}
