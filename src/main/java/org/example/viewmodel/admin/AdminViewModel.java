package org.example.viewmodel.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.model.Booking;

public class AdminViewModel {
    private ObservableList<Booking> bookings;

    public AdminViewModel() {

        bookings = FXCollections.observableArrayList();

    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);

    }
}
