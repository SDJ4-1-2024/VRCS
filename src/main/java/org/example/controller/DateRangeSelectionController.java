package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.example.controller.vehicle.available.AvailableCarsController;
import org.example.controller.vehicle.available.AvailableTrailersController;
import org.example.controller.vehicle.available.AvailableVansController;
import org.example.model.vehicle.VehicleType;

import java.io.IOException;
import java.time.LocalDate;

public class DateRangeSelectionController {
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    private VehicleType vehicleType;

    @FXML
    public void initialize() {
        LocalDate today = LocalDate.now();
        startDatePicker.setValue(today);
        endDatePicker.setValue(today.plusDays(1));
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @FXML
    private void goToAvailableVehicles() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        try {
            if (vehicleType.equals(VehicleType.CAR)){
                prepareCarView(startDate, endDate);
            }
            if (vehicleType.equals(VehicleType.VAN)){
                prepareVanView(startDate, endDate);
            }
            if (vehicleType.equals(VehicleType.TRAILER)){
                prepareTrailerView(startDate, endDate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareCarView(LocalDate startDate, LocalDate endDate) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/available/AvailableCarsView.fxml"));
        Parent root = loader.load();
        AvailableCarsController controller = loader.getController();
        controller.setDetails(vehicleType, startDate, endDate);

        Stage stage = (Stage) startDatePicker.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    private void prepareVanView(LocalDate startDate, LocalDate endDate) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/available/AvailableVansView.fxml"));
        Parent root = loader.load();
        AvailableVansController controller = loader.getController();
        controller.setDetails(vehicleType, startDate, endDate);

        Stage stage = (Stage) startDatePicker.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    private void prepareTrailerView(LocalDate startDate, LocalDate endDate) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/available/AvailableTrailersView.fxml"));
        Parent root = loader.load();
        AvailableTrailersController controller= loader.getController();
        controller.setDetails(vehicleType, startDate, endDate);

        Stage stage = (Stage) startDatePicker.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
