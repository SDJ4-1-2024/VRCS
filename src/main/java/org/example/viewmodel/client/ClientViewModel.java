package org.example.viewmodel.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Booking;

public class ClientViewModel {
    private ObservableList<Booking> bookings;

    public ClientViewModel() {
        
        bookings = FXCollections.observableArrayList();
        
        
    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        
    }
}
