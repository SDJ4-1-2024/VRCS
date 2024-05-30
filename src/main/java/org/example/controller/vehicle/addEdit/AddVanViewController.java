package org.example.controller.vehicle.addEdit;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.vehicle.Van;
import org.example.model.vehicle.VanBuilder;
import org.example.model.vehicle.VehicleType;
import org.example.repository.vehicle.VehicleRepository;
import org.example.viewmodel.vehicle.VanViewModel;

public class AddVanViewController {
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
    private TextField hpField;
    @FXML
    private TextField pricePerDayField;

    private ObservableList<VanViewModel> vans;
    private VanViewModel vanViewModel;

    public void setViewModel(ObservableList<VanViewModel> vans, VanViewModel vanViewModel) {
        this.vans = vans;
        this.vanViewModel = vanViewModel;

        if (vanViewModel != null) {
            makeField.setText(vanViewModel.getMake());
            brandField.setText(vanViewModel.getBrand());
            registrationPlateField.setText(vanViewModel.getRegistrationPlate());
            trunkSpaceHeightField.setText(String.valueOf(vanViewModel.getTrunkSpaceHeight()));
            trunkSpaceWidthField.setText(String.valueOf(vanViewModel.getTrunkSpaceWidth()));
            carryingCapacityField.setText(String.valueOf(vanViewModel.getCarryingCapacity()));
            hpField.setText(String.valueOf(vanViewModel.getHp()));
            pricePerDayField.setText(String.valueOf(vanViewModel.getPricePerDay()));
        }
    }

    @FXML
    private void saveVan() {
        String make = makeField.getText();
        String brand = brandField.getText();
        String registrationPlate = registrationPlateField.getText();
        int trunkSpaceHeight = Integer.parseInt(trunkSpaceHeightField.getText());
        int trunkSpaceWidth = Integer.parseInt(trunkSpaceWidthField.getText());
        int carryingCapacity = Integer.parseInt(carryingCapacityField.getText());
        int hp = Integer.parseInt(hpField.getText());
        int pricePerDay = Integer.parseInt(pricePerDayField.getText());

        Van van = new VanBuilder().setMake(make).setBrand(brand).setRegistrationPlate(registrationPlate).setVehicleType(VehicleType.VAN).setPricePerDay(pricePerDay)
                .setTrunkSpaceHeight(trunkSpaceHeight).setTrunkSpaceWidth(trunkSpaceWidth).setCarryingCapacity(carryingCapacity).setHp(hp).createVan();
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.saveVehicle(van);
        if (vanViewModel == null) {
            VanViewModel newVanViewModel = new VanViewModel(van);
            vans.add(newVanViewModel);
        } else {
            vanViewModel = new VanViewModel(van);
            vans.remove(vanViewModel);
            vans.add(vanViewModel);
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
