package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.model.Booking;
import org.example.model.vehicle.CarBuilder;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleType;
import org.example.repository.BookingsRepository;
import org.example.repository.vehicle.VehicleRepository;
import org.example.viewmodel.VehicleViewModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailableVehiclesController {

    @FXML
    private TableView<VehicleViewModel> vehicleViewModelTableView;
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
        vehicles.add(new CarBuilder().setMake("Toyota").setBrand("Corolla").setRegistrationPlate("ABC123").setVehicleType(vehicleType).setPricePerDay(50).setNumberOfSeats(5).setTrunkCapacity(450).setHp(150).createCar());
        vehicles.add(new CarBuilder().setMake("Honda").setBrand("Civic").setRegistrationPlate("XYZ789").setVehicleType(vehicleType).setPricePerDay(55).setNumberOfSeats(5).setTrunkCapacity(400).setHp(182).createCar());
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