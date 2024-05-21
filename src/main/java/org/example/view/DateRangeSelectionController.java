package org.example.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.example.model.vehicle.VehicleType;

import java.io.IOException;
import java.time.LocalDate;

public class DateRangeSelectionController {
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    private VehicleType vehicleType;

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @FXML
    private void goToAvailableVehicles() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AvailableVehiclesView.fxml"));
            Parent root = loader.load();
            AvailableVehiclesController controller = loader.getController();
            controller.setDetails(vehicleType, startDate, endDate);

            Stage stage = (Stage) startDatePicker.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
