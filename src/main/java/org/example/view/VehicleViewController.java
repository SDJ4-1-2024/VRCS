package org.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.viewmodel.VehicleViewModel;

public class VehicleViewController {

    @FXML
    private TextField makeField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField registrationPlateField;
    @FXML
    private TextField vehicleTypeField;
    @FXML
    private TextField pricePerDayField;
    @FXML
    private TextField rentedField;
    @FXML
    private TextField bookedField;

    private VehicleViewModel viewModel;

    public void setViewModel(VehicleViewModel viewModel) {
        this.viewModel = viewModel;

        makeField.textProperty().bindBidirectional(viewModel.makeProperty());
        brandField.textProperty().bindBidirectional(viewModel.brandProperty());
        registrationPlateField.textProperty().bindBidirectional(viewModel.registrationPlateProperty());
        vehicleTypeField.textProperty().bind(viewModel.vehicleTypeProperty().asString());
        pricePerDayField.textProperty().bind(viewModel.pricePerDayProperty().asString());
        rentedField.textProperty().bind(viewModel.rentedProperty().asString());
        bookedField.textProperty().bind(viewModel.bookedProperty().asString());
    }
}
