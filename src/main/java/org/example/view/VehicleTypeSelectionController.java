package org.example.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.example.model.vehicle.VehicleType;

import java.io.IOException;

public class VehicleTypeSelectionController {
    @FXML private ComboBox<String> vehicleTypeComboBox;

    @FXML
    private void goToDateRangeSelection() {
        String selectedType = vehicleTypeComboBox.getValue();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DateRangeSelectionView.fxml"));
            Parent root = loader.load();
            DateRangeSelectionController controller = loader.getController();
            controller.setVehicleType(VehicleType.valueOf(selectedType.toUpperCase()));

            Stage stage = (Stage) vehicleTypeComboBox.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
