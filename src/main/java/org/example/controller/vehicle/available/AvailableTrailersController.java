package org.example.controller.vehicle.available;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.model.vehicle.Trailer;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleType;
import org.example.repository.BookingsRepository;
import org.example.repository.ClientRepository;
import org.example.repository.vehicle.VehicleRepository;
import org.example.service.vehicle.TrailerService;
import org.example.util.LocalDateConverter;
import org.example.util.PopUpUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.prefs.Preferences;

public class AvailableTrailersController {

    @FXML
    private TableView<Trailer> availableTrailersTable;
    @FXML
    private TableColumn<Trailer, String> makeColumn;
    @FXML
    private TableColumn<Trailer, String> brandColumn;
    @FXML
    private TableColumn<Trailer, String> registrationPlateColumn;
    @FXML
    private TableColumn<Trailer, String> vehicleTypeColumn;
    @FXML
    private TableColumn<Trailer, Integer> pricePerDayColumn;
    @FXML
    private TableColumn<Trailer, Integer> trunkSpaceHeightColumn;
    @FXML
    private TableColumn<Trailer, Integer> trunkSpaceWidthColumn;
    @FXML
    private TableColumn<Trailer, Integer> carryingCapacityColumn;

    private VehicleType vehicleType;
    private LocalDate startDate;
    private LocalDate endDate;
    private ObservableList<Trailer> availableTrailersData = FXCollections.observableArrayList();

    VehicleRepository vehicleRepository;
    ClientRepository clientRepository;

    public TextField phoneTextField;

    @FXML
    public void initialize() {
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        registrationPlateColumn.setCellValueFactory(new PropertyValueFactory<>("registrationPlate"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
        trunkSpaceHeightColumn.setCellValueFactory(new PropertyValueFactory<>("trunkSpaceHeight"));
        trunkSpaceWidthColumn.setCellValueFactory(new PropertyValueFactory<>("trunkSpaceWidth"));
        carryingCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("carryingCapacity"));

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
        List<Vehicle> vehicles = vehicleRepository.loadAvailableVehiclesInTimePeriodRange(startDate, endDate, vehicleType);
        TrailerService trailerService = new TrailerService();
        List<Trailer> availableTrailers = trailerService.castVehiclesToTrailers(vehicles);
        prepareAvailableTrailers(availableTrailers);
    }

    private void prepareAvailableTrailers(List<Trailer> availableTrailers) {
        availableTrailersData.clear();
        availableTrailersData.addAll(availableTrailers);
        availableTrailersTable.setItems(availableTrailersData);
    }

    @FXML
    private void bookTrailer() {
        Trailer selectedVehicle = availableTrailersTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            BookingsRepository bookingRepository = new BookingsRepository();
            int trailerId = vehicleRepository.prepareVehicleIdByRegPlate(selectedVehicle.getRegistrationPlate())
                    .orElseThrow();
            String phone = Preferences.userRoot().get("phone", null);
            if (!phoneTextField.getText().trim().isEmpty()) {
                phone = phoneTextField.getText();
            }
            Optional<Integer> clientIdInit = clientRepository.prepareClientIdByPhoneNumber(phone);
            if (clientIdInit.isEmpty()) {
                PopUpUtil.popUpError("Client was not found", "Change client's phone number to the existing one");
            } else {
                int clientId = clientIdInit.orElseThrow();
                bookingRepository.saveBooking(LocalDateConverter.convertToDatabaseColumn(startDate),
                        LocalDateConverter.convertToDatabaseColumn(endDate), trailerId, clientId);
                PopUpUtil.popUpInfo("Booking success", selectedVehicle.getMake() + " " + selectedVehicle.getBrand() + " has been booked for: " + startDate + " to " + endDate);
                closeOrCancel();
            }
        }
    }

    @FXML
    private void closeOrCancel() {
        Stage stage = (Stage) availableTrailersTable.getScene().getWindow();
        stage.close();
    }
}
