package org.example.viewmodel.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Booking;

public class ClientViewModel {
    private ObservableList<Booking> bookings;

    public ClientViewModel() {
        // Initialize with some dummy data or fetch from the database
        bookings = FXCollections.observableArrayList();
        // Example:
        // bookings.add(new Booking(...));
    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        // Add logic to remove from the database if necessary
    }
}
