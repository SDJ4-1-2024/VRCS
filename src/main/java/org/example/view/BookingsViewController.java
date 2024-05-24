package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Booking;
import org.example.model.client.Client;
import org.example.model.client.ClientType;
import org.example.model.vehicle.Car;
import org.example.model.vehicle.VehicleType;
import org.example.viewmodel.BookingViewModel;

import java.time.LocalDate;
import java.util.List;

public class BookingsViewController {

    @FXML
    private TableView<BookingViewModel> bookingsTableView;
    @FXML
    private TableColumn<BookingViewModel, String> clientColumn;
    @FXML
    private TableColumn<BookingViewModel, String> vehicleColumn;
    @FXML
    private TableColumn<BookingViewModel, String> vehicleTypeColumn;
    @FXML
    private TableColumn<BookingViewModel, String> startDateColumn;
    @FXML
    private TableColumn<BookingViewModel, String> endDateColumn;

    private ObservableList<BookingViewModel> bookingData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientPhoneNumber"));
        vehicleColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationPlate"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        // Load bookings data here
        loadBookings();
    }

    private void loadBookings() {
        // Example data, replace with actual data loading logic
        // Assume getBookings() returns a list of Booking objects
        for (Booking booking : getBookings()) {
            bookingData.add(new BookingViewModel(booking));
        }
        bookingsTableView.setItems(bookingData);
    }

    private List<Booking> getBookings() {
        // Placeholder method to simulate loading bookings
        return List.of(new Booking(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 10), new Car("Toyota", "Corolla", "REG001", VehicleType.CAR, 50, 5, 500, 150), new Client("123456789", ClientType.PERSONAL )));
    }

    @FXML
    private void handleAdd() {
        // Logic to add a booking
    }

    @FXML
    private void handleEdit() {
        // Logic to edit a booking
    }

    @FXML
    private void handleRemove() {
        // Logic to remove a booking
    }
}
