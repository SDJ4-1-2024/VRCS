package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageVehiclesViewController {

    @FXML
    private void manageCars() {
        openManagementView("/vehicle/CarView.fxml");
    }

    @FXML
    private void manageVans() {
        openManagementView("/vehicle/VanView.fxml");
    }

    @FXML
    private void manageTrailers() {
        openManagementView("/vehicle/TrailerView.fxml");
    }

    private void openManagementView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Manage Vehicles");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
