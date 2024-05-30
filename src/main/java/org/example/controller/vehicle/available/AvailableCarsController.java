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
import org.example.model.vehicle.Car;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleType;
import org.example.repository.BookingsRepository;
import org.example.repository.ClientRepository;
import org.example.repository.vehicle.VehicleRepository;
import org.example.service.vehicle.CarService;
import org.example.util.ClientUtils;
import org.example.util.LocalDateConverter;
import org.example.util.PopUpUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
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
    private TextField phoneTextField;
    private RefreshableBookingsController refreshableBookingsController;

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
        CarService carService = new CarService();
        List<Car> availableCars = carService.castVehiclesToCars(vehicles);
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
            int carId = vehicleRepository.prepareVehicleIdByRegPlate(selectedVehicle.getRegistrationPlate())
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
                        LocalDateConverter.convertToDatabaseColumn(endDate), carId, clientId);
                long totalPrice = ChronoUnit.DAYS.between(startDate, endDate) * selectedVehicle.getPricePerDay();
                PopUpUtil.popUpInfo("Booking success",
                        selectedVehicle.getMake()+" "+selectedVehicle.getBrand()+" has been booked for: "
                                +startDate+" to "+endDate+". For the total price of: "+ totalPrice
                                +" You can pickup car at 10:00 on the booking start date and it needs to be returned before 09:00 at the booking end date");
                refreshableBookingsController.refreshBookings();
                closeOrCancel();
            }
        }
    }


    @FXML
    private void closeOrCancel() {
        Stage stage = (Stage) availableCarsTable.getScene().getWindow();
        stage.close();
    }

    public void setRefreshableBookingsController(RefreshableBookingsController refreshableBookingsController) {
        this.refreshableBookingsController = refreshableBookingsController;
    }
}
