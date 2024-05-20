package org.example.viewmodel.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminViewModel {

    private ObservableList<String> vehicles = FXCollections.observableArrayList();
    private ObservableList<String> bookings = FXCollections.observableArrayList();

    public AdminViewModel() {
        // Initialize with some data
        vehicles.addAll("Car 1", "Car 2", "Van 1", "Trailer 1");
    }

    public ObservableList<String> getVehicles() {
        return vehicles;
    }

    public ObservableList<String> getBookings() {
        return bookings;
    }

    public void addVehicle(String vehicle) {
        vehicles.add(vehicle);
        // Notify server
        // ...
    }

    public void removeVehicle(String vehicle) {
        vehicles.remove(vehicle);
        // Notify server
        // ...
    }

    public void removeBooking(String booking) {
        bookings.remove(booking);
        // Notify server
        // ...
    }
}
