package org.example.controller.vehicle.addEdit;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.vehicle.Van;
import org.example.model.vehicle.VanBuilder;
import org.example.model.vehicle.VehicleType;
import org.example.repository.vehicle.VehicleRepository;
import org.example.viewmodel.vehicle.VanViewModel;

public class EditVanController {

    @FXML
    private TextField makeField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField registrationPlateField;
    @FXML
    private TextField pricePerDayField;
    @FXML
    private TextField trunkSpaceHeightField;
    @FXML
    private TextField trunkSpaceWidthField;
    @FXML
    private TextField carryingCapacityField;
    @FXML
    private TextField hpField;

    private VehicleRepository vehicleRepository;
    private VanViewModel vanViewModel;

    public void initialize() {
        vehicleRepository = new VehicleRepository();
    }

    public void setVan(VanViewModel vanViewModel) {
        this.vanViewModel = vanViewModel;
        populateFields();
    }

    private void populateFields() {
        makeField.setText(vanViewModel.getMake());
        brandField.setText(vanViewModel.getBrand());
        registrationPlateField.setText(vanViewModel.getRegistrationPlate());
        pricePerDayField.setText(String.valueOf(vanViewModel.getPricePerDay()));
        trunkSpaceHeightField.setText(String.valueOf(vanViewModel.getTrunkSpaceHeight()));
        trunkSpaceWidthField.setText(String.valueOf(vanViewModel.getTrunkSpaceWidth()));
        carryingCapacityField.setText(String.valueOf(vanViewModel.getCarryingCapacity()));
        hpField.setText(String.valueOf(vanViewModel.getHp()));
    }

    @FXML
    private void saveVan() {
        Van van = new VanBuilder().setMake(makeField.getText()).setBrand(brandField.getText())
                .setRegistrationPlate(registrationPlateField.getText())
                .setVehicleType(VehicleType.VAN)
                .setPricePerDay(Integer.valueOf(pricePerDayField.getText()))
                .setTrunkSpaceHeight(Integer.parseInt(trunkSpaceHeightField.getText()))
                .setTrunkSpaceWidth(Integer.parseInt(trunkSpaceWidthField.getText()))
                .setCarryingCapacity(Integer.parseInt(carryingCapacityField.getText()))
                .setHp(Integer.parseInt(hpField.getText())).createVan();

        vehicleRepository.updateVehicle(van);

        Stage stage = (Stage) makeField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) makeField.getScene().getWindow();
        stage.close();
    }
}
