package org.example.controller.vehicle.addEdit;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.vehicle.Car;
import org.example.model.vehicle.CarBuilder;
import org.example.model.vehicle.VehicleType;
import org.example.repository.vehicle.CarRepository;
import org.example.repository.vehicle.VehicleRepository;
import org.example.viewmodel.vehicle.CarViewModel;

public class AddEditCarViewController {
    @FXML private TextField makeField;
    @FXML private TextField brandField;
    @FXML private TextField registrationPlateField;
    @FXML private TextField numberOfSeatsField;
    @FXML private TextField trunkCapacityField;
    @FXML private TextField hpField;
    @FXML private TextField pricePerDayField;

    private ObservableList<CarViewModel> cars;
    private CarViewModel carViewModel;

    public void setViewModel(ObservableList<CarViewModel> cars, CarViewModel carViewModel) {
        this.cars = cars;
        this.carViewModel = carViewModel;

        if (carViewModel != null) {
            makeField.setText(carViewModel.getMake());
            brandField.setText(carViewModel.getBrand());
            registrationPlateField.setText(carViewModel.getRegistrationPlate());
            numberOfSeatsField.setText(String.valueOf(carViewModel.getNumberOfSeats()));
            trunkCapacityField.setText(String.valueOf(carViewModel.getTrunkCapacity()));
            hpField.setText(String.valueOf(carViewModel.getHp()));
            pricePerDayField.setText(String.valueOf(carViewModel.getPricePerDay()));
        }
    }

    @FXML
    private void saveCar() {
        String make = makeField.getText();
        String brand = brandField.getText();
        String registrationPlate = registrationPlateField.getText();
        int numberOfSeats = Integer.parseInt(numberOfSeatsField.getText());
        int trunkCapacity = Integer.parseInt(trunkCapacityField.getText());
        int hp = Integer.parseInt(hpField.getText());
        int pricePerDay = Integer.parseInt(pricePerDayField.getText());
        Car car = new CarBuilder().setMake(make).setBrand(brand).setRegistrationPlate(registrationPlate).setVehicleType(VehicleType.CAR)
                .setPricePerDay(pricePerDay).setNumberOfSeats(numberOfSeats).setTrunkCapacity(trunkCapacity).setHp(hp).createCar();
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.saveVehicle(car);
        if (carViewModel == null) {
            CarViewModel newCarViewModel = new CarViewModel(car);
            cars.add(newCarViewModel);
        } else {
            carViewModel = new CarViewModel(car);
            cars.remove(carViewModel);
            cars.add(carViewModel);
        }
        closeDialog();
    }

    @FXML
    private void cancel() {
        closeDialog();
    }

    private void closeDialog() {
        Stage stage = (Stage) makeField.getScene().getWindow();
        stage.close();
    }
}
