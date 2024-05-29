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
import org.example.util.PopUpUtil;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateRangeSelectionController {
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    private VehicleType vehicleType;

    private RefreshableBookingsController refreshableBookingsController;

    private static final int RENTAL_DAYS_LIMIT = 30;
    private static final String WRONG_DATES_LABEL = "Wrong dates";

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
        if (datesAreFine(startDate, endDate)) {
            try {
                if (vehicleType.equals(VehicleType.CAR)) {
                    prepareCarView(startDate, endDate);
                }
                if (vehicleType.equals(VehicleType.VAN)) {
                    prepareVanView(startDate, endDate);
                }
                if (vehicleType.equals(VehicleType.TRAILER)) {
                    prepareTrailerView(startDate, endDate);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean datesAreFine(LocalDate startDate, LocalDate endDate) {
        LocalDate today = LocalDate.now();

        if (startDate.isBefore(today) || endDate.isBefore(today)) {
            PopUpUtil.popUpError(WRONG_DATES_LABEL, "Dates cannot be in the past");
            return false;
        }
        if (endDate.isBefore(startDate)){
            PopUpUtil.popUpError(WRONG_DATES_LABEL,"The end date cannot be earlier than the start date");
            return false;
        }
        if (endDate.equals(startDate)){
            PopUpUtil.popUpError(WRONG_DATES_LABEL,"You need to book a vehicle for at least 1 day");
            return false;
        }
        if (ChronoUnit.DAYS.between(startDate, endDate) > RENTAL_DAYS_LIMIT) {
            PopUpUtil.popUpInfo("Too long booking period", "You cannot book a car for more than 30 days");
            return false;
        }
        return true;
    }

    private void prepareCarView(LocalDate startDate, LocalDate endDate) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/available/AvailableCarsView.fxml"));
        Parent root = loader.load();
        AvailableCarsController controller = loader.getController();
        controller.setDetails(vehicleType, startDate, endDate);
        controller.setRefreshableBookingsController(refreshableBookingsController);

        Stage stage = (Stage) startDatePicker.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void prepareVanView(LocalDate startDate, LocalDate endDate) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/available/AvailableVansView.fxml"));
        Parent root = loader.load();
        AvailableVansController controller = loader.getController();
        controller.setDetails(vehicleType, startDate, endDate);
        controller.setRefreshableBookingsController(refreshableBookingsController);

        Stage stage = (Stage) startDatePicker.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void prepareTrailerView(LocalDate startDate, LocalDate endDate) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/available/AvailableTrailersView.fxml"));
        Parent root = loader.load();
        AvailableTrailersController controller = loader.getController();
        controller.setDetails(vehicleType, startDate, endDate);
        controller.setRefreshableBookingsController(refreshableBookingsController);

        Stage stage = (Stage) startDatePicker.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void setRefreshableBookingsController(RefreshableBookingsController refreshableBookingsController) {
        this.refreshableBookingsController = refreshableBookingsController;
    }
}
