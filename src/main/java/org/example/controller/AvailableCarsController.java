package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.model.vehicle.Car;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleType;
import org.example.repository.vehicle.VehicleRepository;
import org.example.viewmodel.vehicle.CarViewModel;

import java.time.LocalDate;
import java.util.List;

public class AvailableCarsController {

    @FXML
    private TableView<CarViewModel> carViewModelTableView;

    @FXML
    private TableView<Car> availableCarsTable;
    @FXML
    private TableColumn<Car, String> makeColumn;
    @FXML
    private TableColumn<Car, String> brandColumn;
    @FXML
    private TableColumn<Car, String> registrationPlateColumn;
    @FXML
    private TableColumn<Car, String> vehicleTypeColumn;
    @FXML
    private TableColumn<Car, Integer> pricePerDayColumn;
    @FXML
    private TableColumn<Car, Integer> numberOfSeats;
    @FXML
    private TableColumn<Car, Integer> trunkCapacity;
    @FXML
    private TableColumn<Car, Integer> hp;

    private VehicleType vehicleType;
    private LocalDate startDate;
    private LocalDate endDate;
    private ObservableList<CarViewModel> availableCarsData = FXCollections.observableArrayList();


    private void prepareAvailableCars(List<Car> availableCars) {
        for (Car car : availableCars) {
            availableCarsData.add(new CarViewModel(car));
        }
        carViewModelTableView.setItems(availableCarsData);
    }

    @FXML
    private void bookCar() {
        Car selectedVehicle = availableCarsTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            System.out.println(selectedVehicle.getTrunkCapacity());
        }
    }

    @FXML
    private void cancel() {

        Stage stage = (Stage) availableCarsTable.getScene().getWindow();
        stage.close();
    }

    public void setDetails(VehicleType vehicleType, LocalDate startDate, LocalDate endDate) {
        this.vehicleType = vehicleType;
        this.startDate = startDate;
        this.endDate = endDate;
        loadAvailableVehicles();
    }

    private void loadAvailableVehicles() {

        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        registrationPlateColumn.setCellValueFactory(new PropertyValueFactory<>("registrationPlate"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
        numberOfSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        trunkCapacity.setCellValueFactory(new PropertyValueFactory<>("trunkCapacity"));
        hp.setCellValueFactory(new PropertyValueFactory<>("hp"));

        VehicleRepository vehicleRepository = new VehicleRepository();
        prepareAvailableCars(vehicleRepository.loadAvailableCarsInTimePeriodRange(startDate, endDate, vehicleType));
    }
}
