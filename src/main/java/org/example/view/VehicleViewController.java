package org.example.view;

import javafx.beans.property.Property;
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
    private TextField rentedField;
    @FXML
    private TextField bookedField;

    private VehicleViewModel viewModel;

    public void setViewModel(VehicleViewModel viewModel) {
        this.viewModel = viewModel;

        makeField.textProperty().bindBidirectional(viewModel.makeProperty());
        brandField.textProperty().bindBidirectional(viewModel.brandProperty());
        registrationPlateField.textProperty().bindBidirectional(viewModel.registrationPlateProperty());
        vehicleTypeField.textProperty().bindBidirectional((Property<String>) viewModel.vehicleTypeProperty().asString());
    }
}
