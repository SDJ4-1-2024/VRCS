package org.example.view.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.model.Booking;
import org.example.viewmodel.admin.AdminViewModel;

import java.io.IOException;

public class AdminViewController {
    @FXML private TableView<Booking> bookingsTable;
    @FXML private TableColumn<Booking, String> clientColumn;
    @FXML private TableColumn<Booking, String> vehicleColumn;
    @FXML private TableColumn<Booking, String> startDateColumn;
    @FXML private TableColumn<Booking, String> endDateColumn;

    private AdminViewModel viewModel;

    public void setViewModel(AdminViewModel viewModel) {
        this.viewModel = viewModel;
        bookingsTable.setItems(viewModel.getBookings());

        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        vehicleColumn.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    }

    @FXML
    private void goToVehicleTypeSelection() {
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
    private void removeBooking() {
        Booking selectedBooking = bookingsTable.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            viewModel.removeBooking(selectedBooking);
        }
    }
}
