package org.example.controller.vehicle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.controller.vehicle.addEdit.EditCarController;
import org.example.controller.vehicle.addEdit.AddCarViewController;
import org.example.model.vehicle.Car;
import org.example.network.ClientRequestHandler;
import org.example.repository.vehicle.VehicleRepository;
import org.example.util.PopUpUtil;
import org.example.viewmodel.vehicle.CarViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class CarViewController {
    @FXML
    private TableView<CarViewModel> carTable;
    @FXML
    private TableColumn<CarViewModel, String> makeColumn;
    @FXML
    private TableColumn<CarViewModel, String> brandColumn;
    @FXML
    private TableColumn<CarViewModel, String> registrationPlateColumn;
    @FXML
    private TableColumn<CarViewModel, Integer> numberOfSeatsColumn;
    @FXML
    private TableColumn<CarViewModel, Integer> trunkCapacityColumn;
    @FXML
    private TableColumn<CarViewModel, Integer> hpColumn;
    @FXML
    private TableColumn<CarViewModel, Integer> pricePerDayColumn;

    private ObservableList<CarViewModel> carsData;

    private VehicleRepository vehicleRepository;

    public void initialize() {
        carsData = FXCollections.observableArrayList();
        makeColumn.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        registrationPlateColumn.setCellValueFactory(cellData -> cellData.getValue().registrationPlateProperty());
        numberOfSeatsColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfSeatsProperty().asObject());
        trunkCapacityColumn.setCellValueFactory(cellData -> cellData.getValue().trunkCapacityProperty().asObject());
        hpColumn.setCellValueFactory(cellData -> cellData.getValue().hpProperty().asObject());
        pricePerDayColumn.setCellValueFactory(cellData -> cellData.getValue().pricePerDayProperty().asObject());

        vehicleRepository = new VehicleRepository();
        prepareCars();
    }

    private void prepareCars() {
        List<Car> cars = vehicleRepository.loadCars();
        carsData.clear();
        for (Car car : cars) {
            carsData.add(new CarViewModel(car));
        }
        carTable.setItems(carsData);
    }
    @FXML
    private void localizeVehicle() {
        CarViewModel selectedCar = carTable.getSelectionModel().getSelectedItem();
        PopUpUtil.popUpInfo("Vehicle Localization", selectedCar.getRegistrationPlate() +
                " location is: "+ClientRequestHandler.getResponse("getCoordinates"));
    }

    @FXML
    private void resetVehicleLocation() {
        CarViewModel selectedCar = carTable.getSelectionModel().getSelectedItem();
        PopUpUtil.popUpInfo("Vehicle Localization", selectedCar.getRegistrationPlate() +
                " location is: "+ClientRequestHandler.getResponse("resetCoordinates"));
    }

    @FXML
    private void editCar() {
        CarViewModel selectedCar = carTable.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/addEdit/EditCar.fxml"));
                Parent root = loader.load();

                EditCarController controller = loader.getController();
                controller.setCar(selectedCar);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Edit Car");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                prepareCars();
                carTable.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void addCar() {
        openCarDialog(null);
    }

    @FXML
    private void removeCar() {
        CarViewModel selectedCar = carTable.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            String registrationPlate = selectedCar.getRegistrationPlate();
            VehicleRepository vehicleRepository = new VehicleRepository();
            if (vehicleRepository.isVehicleAvailable(registrationPlate)) {
                vehicleRepository.removeVehicle(registrationPlate);
                carsData.remove(selectedCar);
            } else {
                PopUpUtil.popUpInfo("Cannot remove vehicle", "You cannot remove vehicle which is used for ongoing or future bookings");
            }
        }
    }

    private void openCarDialog(CarViewModel carViewModel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/addEdit/AddCarView.fxml"));
            Parent root = loader.load();

            AddCarViewController controller = loader.getController();
            controller.setViewModel(carsData, carViewModel);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(carViewModel == null ? "Add Car" : "Edit Car");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
