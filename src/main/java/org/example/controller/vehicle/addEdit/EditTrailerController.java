package org.example.controller.vehicle.addEdit;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.vehicle.Trailer;
import org.example.model.vehicle.TrailerBuilder;
import org.example.model.vehicle.VehicleType;
import org.example.repository.vehicle.VehicleRepository;
import org.example.viewmodel.vehicle.TrailerViewModel;

public class EditTrailerController {

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

    private VehicleRepository vehicleRepository;
    private TrailerViewModel trailerViewModel;

    public void initialize() {
        vehicleRepository = new VehicleRepository();
    }

    public void setTrailer(TrailerViewModel trailerViewModel) {
        this.trailerViewModel = trailerViewModel;
        populateFields();
    }

    private void populateFields() {
        makeField.setText(trailerViewModel.getMake());
        brandField.setText(trailerViewModel.getBrand());
        registrationPlateField.setText(trailerViewModel.getRegistrationPlate());
        pricePerDayField.setText(String.valueOf(trailerViewModel.getPricePerDay()));
        trunkSpaceHeightField.setText(String.valueOf(trailerViewModel.getTrunkSpaceHeight()));
        trunkSpaceWidthField.setText(String.valueOf(trailerViewModel.getTrunkSpaceWidth()));
        carryingCapacityField.setText(String.valueOf(trailerViewModel.getCarryingCapacity()));
    }

    @FXML
    private void saveTrailer() {
        Trailer trailer = new TrailerBuilder().setMake(makeField.getText()).setBrand(brandField.getText())
                .setRegistrationPlate(registrationPlateField.getText())
                .setVehicleType(VehicleType.TRAILER)
                .setPricePerDay(Integer.valueOf(pricePerDayField.getText()))
                .setTrunkSpaceHeight(Integer.parseInt(trunkSpaceHeightField.getText()))
                .setTrunkSpaceWidth(Integer.parseInt(trunkSpaceWidthField.getText()))
                .setCarryingCapacity(Integer.parseInt(carryingCapacityField.getText())).createTrailer();

        vehicleRepository.updateVehicle(trailer);

        Stage stage = (Stage) makeField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) makeField.getScene().getWindow();
        stage.close();
    }
}

