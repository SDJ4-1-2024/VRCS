package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.model.vehicle.Car;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailableVehiclesController {
    @FXML private TableView<Vehicle> availableVehiclesTable;
    @FXML private TableColumn<Vehicle, String> makeColumn;
    @FXML private TableColumn<Vehicle, String> brandColumn;
    @FXML private TableColumn<Vehicle, String> registrationPlateColumn;
    @FXML private TableColumn<Vehicle, String> vehicleTypeColumn;
    @FXML private TableColumn<Vehicle, Integer> pricePerDayColumn;

    private VehicleType vehicleType;
    private LocalDate startDate;
    private LocalDate endDate;

    public void setDetails(VehicleType vehicleType, LocalDate startDate, LocalDate endDate) {
        this.vehicleType = vehicleType;
        this.startDate = startDate;
        this.endDate = endDate;
        loadAvailableVehicles();
    }

    private void loadAvailableVehicles() {
        
        List<Vehicle> availableVehicles = fetchAvailableVehicles(vehicleType, startDate, endDate);
        ObservableList<Vehicle> observableList = FXCollections.observableArrayList(availableVehicles);
        availableVehiclesTable.setItems(observableList);
    }

    private List<Vehicle> fetchAvailableVehicles(VehicleType vehicleType, LocalDate startDate, LocalDate endDate) {
        
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Car("Toyota", "Corolla", "ABC123", vehicleType, 50, 5, 450, 150));
        vehicles.add(new Car("Honda", "Civic", "XYZ789", vehicleType, 55, 5, 400, 182));
        return vehicles;
    }

    @FXML
    private void bookVehicle() {
        Vehicle selectedVehicle = availableVehiclesTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            
        }
    }

    @FXML
    private void cancel() {
        
        Stage stage = (Stage) availableVehiclesTable.getScene().getWindow();
        stage.close();
    }
}