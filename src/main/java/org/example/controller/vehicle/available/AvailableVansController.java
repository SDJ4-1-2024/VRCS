package org.example.controller.vehicle.available;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.controller.RefreshableBookingsController;
import org.example.model.vehicle.Van;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleType;
import org.example.repository.BookingsRepository;
import org.example.repository.ClientRepository;
import org.example.repository.vehicle.VehicleRepository;
import org.example.service.vehicle.VanService;
import org.example.util.ClientUtils;
import org.example.util.LocalDateConverter;
import org.example.util.PopUpUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.prefs.Preferences;

public class AvailableVansController {
    @FXML
    private TableView<Van> availableVansTable;
    @FXML
    private TableColumn<Van, String> makeColumn;
    @FXML
    private TableColumn<Van, String> brandColumn;
    @FXML
    private TableColumn<Van, String> registrationPlateColumn;
    @FXML
    private TableColumn<Van, String> vehicleTypeColumn;
    @FXML
    private TableColumn<Van, Integer> pricePerDayColumn;
    @FXML
    private TableColumn<Van, Integer> trunkSpaceHeightColumn;
    @FXML
    private TableColumn<Van, Integer> trunkSpaceWidthColumn;
    @FXML
    private TableColumn<Van, Integer> carryingCapacityColumn;
    @FXML
    private TableColumn<Van, Integer> hpColumn;

    private VehicleType vehicleType;
    private LocalDate startDate;
    private LocalDate endDate;
    private ObservableList<Van> availableVansData = FXCollections.observableArrayList();

    VehicleRepository vehicleRepository;
    ClientRepository clientRepository;

    public TextField phoneTextField;
    private RefreshableBookingsController refreshableBookingsController;

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
        hpColumn.setCellValueFactory(new PropertyValueFactory<>("hp"));

        vehicleRepository = new VehicleRepository();
        clientRepository = new ClientRepository();

        setPhoneTFVisibility();
    }

    private void setPhoneTFVisibility() {
        if (ClientUtils.isAdmin()){
            phoneTextField.setVisible(true);
        }
    }

    public void setDetails(VehicleType vehicleType, LocalDate startDate, LocalDate endDate) {
        this.vehicleType = vehicleType;
        this.startDate = startDate;
        this.endDate = endDate;
        loadAvailableVehicles();
    }

    private void loadAvailableVehicles() {
        List<Vehicle> vehicles = vehicleRepository.loadAvailableVehiclesInTimePeriodRange(startDate, endDate, vehicleType);
        VanService vanService = new VanService();
        List<Van> availableVans = vanService.castVehiclesToVans(vehicles);
        prepareAvailableVans(availableVans);
    }

    private void prepareAvailableVans(List<Van> availableVans) {
        availableVansData.clear();
        availableVansData.addAll(availableVans);
        availableVansTable.setItems(availableVansData);
    }

    @FXML
    private void bookVan() {
        Van selectedVehicle = availableVansTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            BookingsRepository bookingRepository = new BookingsRepository();
            int vanId = vehicleRepository.prepareVehicleIdByRegPlate(selectedVehicle.getRegistrationPlate())
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
                        LocalDateConverter.convertToDatabaseColumn(endDate), vanId, clientId);
                int totalPrice = (int) (ChronoUnit.DAYS.between(startDate, endDate) * selectedVehicle.getPricePerDay());
                PopUpUtil.popUpInfo("Booking success", selectedVehicle.getMake()
                        + " " + selectedVehicle.getBrand() + " has been booked for: " + startDate + " to " + endDate
                        +". For the total price of: "+ totalPrice +" You can pickup car at 10:00 on the booking start " +
                        "date and it needs to be returned before 09:00 at the booking end date");
                refreshableBookingsController.refreshBookings();
                closeOrCancel();
            }
        }
    }

    @FXML
    private void closeOrCancel() {
        Stage stage = (Stage) availableVansTable.getScene().getWindow();
        stage.close();
    }

    public void setRefreshableBookingsController(RefreshableBookingsController refreshableBookingsController) {
        this.refreshableBookingsController = refreshableBookingsController;
    }
}
