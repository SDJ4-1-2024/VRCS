package org.example.controller.vehicle.addEdit;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.vehicle.Car;
import org.example.model.vehicle.CarBuilder;
import org.example.model.vehicle.VehicleType;
import org.example.repository.vehicle.CarRepository;
import org.example.repository.vehicle.VehicleRepository;
import org.example.viewmodel.vehicle.CarViewModel;

public class EditCarController {

    @FXML
    private TextField makeField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField registrationPlateField;
    @FXML
    private TextField pricePerDayField;
    @FXML
    private TextField numberOfSeatsField;
    @FXML
    private TextField trunkCapacityField;
    @FXML
    private TextField hpField;

    private VehicleRepository vehicleRepository;
    private CarViewModel carViewModel;

    public void initialize() {
        vehicleRepository = new VehicleRepository();
    }

    public void setCar(CarViewModel carViewModel) {
        this.carViewModel = carViewModel;
        populateFields();
    }

    private void populateFields() {
        makeField.setText(carViewModel.getMake());
        brandField.setText(carViewModel.getBrand());
        registrationPlateField.setText(carViewModel.getRegistrationPlate());
        pricePerDayField.setText(String.valueOf(carViewModel.getPricePerDay()));
        numberOfSeatsField.setText(String.valueOf(carViewModel.getNumberOfSeats()));
        trunkCapacityField.setText(String.valueOf(carViewModel.getTrunkCapacity()));
        hpField.setText(String.valueOf(carViewModel.getHp()));
    }

    @FXML
    private void saveCar() {
        Car car = new CarBuilder().setMake(makeField.getText()).setBrand(brandField.getText())
                .setRegistrationPlate(registrationPlateField.getText())
                .setVehicleType(VehicleType.CAR)
                .setPricePerDay(Integer.valueOf(pricePerDayField.getText()))
                .setNumberOfSeats(Integer.parseInt(numberOfSeatsField.getText()))
                .setTrunkCapacity(Integer.parseInt(trunkCapacityField.getText()))
                .setHp(Integer.parseInt(hpField.getText())).createCar();

        vehicleRepository.updateVehicle(car);

        Stage stage = (Stage) makeField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) makeField.getScene().getWindow();
        stage.close();
    }
}
