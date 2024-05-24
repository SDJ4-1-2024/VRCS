package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.vehicle.Car;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleType;
import org.example.viewmodel.ManageVehicleViewModel;

import java.util.List;

public class ManageVehiclesViewController {

    @FXML
    private TableView<ManageVehicleViewModel> vehiclesTableView;
    @FXML
    private TableColumn<ManageVehicleViewModel, String> makeColumn;
    @FXML
    private TableColumn<ManageVehicleViewModel, String> brandColumn;
    @FXML
    private TableColumn<ManageVehicleViewModel, String> registrationColumn;
    @FXML
    private TableColumn<ManageVehicleViewModel, String> typeColumn;
    @FXML
    private TableColumn<ManageVehicleViewModel, Integer> priceColumn;

    private ObservableList<ManageVehicleViewModel> vehicleData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        registrationColumn.setCellValueFactory(new PropertyValueFactory<>("registrationPlate"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));

        // Load vehicles data here
        loadVehicles();
    }

    private void loadVehicles() {
        // Example data, replace with actual data loading logic
        // Assume getVehicles() returns a list of Vehicle objects
        for (Vehicle vehicle : getVehicles()) {
            vehicleData.add(new ManageVehicleViewModel(vehicle));
        }
        vehiclesTableView.setItems(vehicleData);
    }

    private List<Vehicle> getVehicles() {
        // Placeholder method to simulate loading vehicles
        // Replace with actual data retrieval logic
        return List.of(
                new Car("Toyota", "Corolla", "REG001", VehicleType.CAR, 50, 5, 500, 150),
                new Car("Honda", "Civic", "REG002", VehicleType.CAR, 55, 5, 450, 140));
    }

    @FXML
    private void handleAdd() {
        // Logic to add a vehicle
    }

    @FXML
    private void handleEdit() {
        // Logic to edit a vehicle
    }

    @FXML
    private void handleRemove() {
        // Logic to remove a vehicle
    }
}
