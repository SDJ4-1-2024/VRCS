package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.model.vehicle.Car;
import org.example.model.vehicle.VehicleType;
import org.example.repository.BookingsRepository;
import org.example.repository.ClientRepository;
import org.example.repository.vehicle.VehicleRepository;
import org.example.util.LocalDateConverter;

import java.time.LocalDate;
import java.util.List;
import java.util.prefs.Preferences;

public class AvailableCarsController {

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
    private ObservableList<Car> availableCarsData = FXCollections.observableArrayList();

    VehicleRepository vehicleRepository;
    ClientRepository clientRepository;

    @FXML
    public void initialize() {
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        registrationPlateColumn.setCellValueFactory(new PropertyValueFactory<>("registrationPlate"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
        numberOfSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        trunkCapacity.setCellValueFactory(new PropertyValueFactory<>("trunkCapacity"));
        hp.setCellValueFactory(new PropertyValueFactory<>("hp"));

        vehicleRepository = new VehicleRepository();
        clientRepository = new ClientRepository();
    }

    public void setDetails(VehicleType vehicleType, LocalDate startDate, LocalDate endDate) {
        this.vehicleType = vehicleType;
        this.startDate = startDate;
        this.endDate = endDate;
        loadAvailableVehicles();
    }

    private void loadAvailableVehicles() {
        List<Car> availableCars = vehicleRepository.loadAvailableCarsInTimePeriodRange(startDate, endDate, vehicleType);
        prepareAvailableCars(availableCars);
    }

    private void prepareAvailableCars(List<Car> availableCars) {
        availableCarsData.clear();
        availableCarsData.addAll(availableCars);
        availableCarsTable.setItems(availableCarsData);
    }

    @FXML
    private void bookCar() {
        Car selectedVehicle = availableCarsTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            BookingsRepository bookingRepository = new BookingsRepository();
            int carId = vehicleRepository.prepareVehicleIdByRegPlate(selectedVehicle.getRegistrationPlate()).orElseThrow();
            String phone = Preferences.userRoot().get("phone", null);
            int clientId = clientRepository.prepareClientIdByPhoneNumber(phone).orElseThrow();
            bookingRepository.saveBooking(LocalDateConverter.convertToDatabaseColumn(startDate),
                    LocalDateConverter.convertToDatabaseColumn(endDate), carId,clientId);
        }
    }


    @FXML
    private void cancel() {
        Stage stage = (Stage) availableCarsTable.getScene().getWindow();
        stage.close();
    }
}
