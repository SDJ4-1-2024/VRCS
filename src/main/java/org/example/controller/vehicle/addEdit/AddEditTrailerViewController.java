package org.example.controller.vehicle.addEdit;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.vehicle.Trailer;
import org.example.model.vehicle.TrailerBuilder;
import org.example.model.vehicle.VehicleType;
import org.example.repository.vehicle.TrailerRepository;
import org.example.repository.vehicle.VehicleRepository;
import org.example.viewmodel.vehicle.TrailerViewModel;

public class AddEditTrailerViewController {
    @FXML
    private TextField makeField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField registrationPlateField;
    @FXML
    private TextField trunkSpaceHeightField;
    @FXML
    private TextField trunkSpaceWidthField;
    @FXML
    private TextField carryingCapacityField;
    @FXML
    private TextField pricePerDayField;

    private ObservableList<TrailerViewModel> trailers;
    private TrailerViewModel trailerViewModel;

    public void setViewModel(ObservableList<TrailerViewModel> trailers, TrailerViewModel trailerViewModel) {
        this.trailers = trailers;
        this.trailerViewModel = trailerViewModel;

        if (trailerViewModel != null) {
            makeField.setText(trailerViewModel.getMake());
            brandField.setText(trailerViewModel.getBrand());
            registrationPlateField.setText(trailerViewModel.getRegistrationPlate());
            trunkSpaceHeightField.setText(String.valueOf(trailerViewModel.getTrunkSpaceHeight()));
            trunkSpaceWidthField.setText(String.valueOf(trailerViewModel.getTrunkSpaceWidth()));
            carryingCapacityField.setText(String.valueOf(trailerViewModel.getCarryingCapacity()));
            pricePerDayField.setText(String.valueOf(trailerViewModel.getPricePerDay()));
        }
    }

    @FXML
    private void saveTrailer() {
        String make = makeField.getText();
        String brand = brandField.getText();
        String registrationPlate = registrationPlateField.getText();
        int trunkSpaceHeight = Integer.parseInt(trunkSpaceHeightField.getText());
        int trunkSpaceWidth = Integer.parseInt(trunkSpaceWidthField.getText());
        int carryingCapacity = Integer.parseInt(carryingCapacityField.getText());
        int pricePerDay = Integer.parseInt(pricePerDayField.getText());

        Trailer trailer = new TrailerBuilder().setMake(make).setBrand(brand).setRegistrationPlate(registrationPlate).setVehicleType(VehicleType.TRAILER).setPricePerDay(pricePerDay)
                .setTrunkSpaceHeight(trunkSpaceHeight).setTrunkSpaceWidth(trunkSpaceWidth).setCarryingCapacity(carryingCapacity).createTrailer();

        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.saveVehicle(trailer);
        if (trailerViewModel == null) {
            TrailerViewModel newTrailerViewModel = new TrailerViewModel(trailer);
            trailers.add(newTrailerViewModel);
        } else {
            trailerViewModel = new TrailerViewModel(trailer);
            trailers.remove(trailerViewModel);
            trailers.add(trailerViewModel);
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
