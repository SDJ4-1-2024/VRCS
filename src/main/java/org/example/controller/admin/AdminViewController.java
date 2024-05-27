package org.example.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.viewmodel.admin.AdminViewModel;

import java.io.IOException;

public class AdminViewController {

    private AdminViewModel viewModel;

    public void setViewModel(AdminViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @FXML
    private void openBookingsView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/BookingsView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Manage Bookings");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private void openVehiclesView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ManageVehiclesView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Manage Vehicles");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
