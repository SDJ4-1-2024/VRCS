package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.service.BookingsService;
import org.example.viewmodel.BookingViewModel;

import java.io.IOException;
import java.sql.SQLException;

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

    private final BookingsService bookingsService = new BookingsService();

    @FXML
    public void initialize() throws SQLException {
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientPhoneNumber"));
        vehicleColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationPlate"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        setBookings();
    }

    private void setBookings(){
        bookingData = bookingsService.prepareBookings();
        bookingsTableView.setItems(bookingData);
    }

    @FXML
    private void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VehicleTypeSelectionView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Select Vehicle Type");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEdit() {
        // Logic to edit a booking
    }

    @FXML
    private void handleRemove() {
        BookingViewModel selectedBooking = bookingsTableView.getSelectionModel().getSelectedItem();
        if (bookingsService.isRemoved(selectedBooking)) {
            bookingData.remove(selectedBooking);
        }
    }
}
