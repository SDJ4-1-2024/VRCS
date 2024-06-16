package org.example.controller.vehicle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.controller.vehicle.addEdit.AddTrailerViewController;
import org.example.controller.vehicle.addEdit.EditTrailerController;
import org.example.model.vehicle.Trailer;
import org.example.network.ClientRequestHandler;
import org.example.repository.vehicle.VehicleRepository;
import org.example.util.PopUpUtil;
import org.example.viewmodel.vehicle.CarViewModel;
import org.example.viewmodel.vehicle.TrailerViewModel;

import java.io.IOException;
import java.util.List;

public class TrailerViewController {
    @FXML
    private TableView<TrailerViewModel> trailerTable;
    @FXML
    private TableColumn<TrailerViewModel, String> makeColumn;
    @FXML
    private TableColumn<TrailerViewModel, String> brandColumn;
    @FXML
    private TableColumn<TrailerViewModel, String> registrationPlateColumn;
    @FXML
    private TableColumn<TrailerViewModel, Integer> trunkSpaceHeightColumn;
    @FXML
    private TableColumn<TrailerViewModel, Integer> trunkSpaceWidthColumn;
    @FXML
    private TableColumn<TrailerViewModel, Integer> carryingCapacityColumn;
    @FXML
    private TableColumn<TrailerViewModel, Integer> pricePerDayColumn;
    private ObservableList<TrailerViewModel> trailerData;
    VehicleRepository vehicleRepository;

    public void initialize() {
        trailerData = FXCollections.observableArrayList();
        makeColumn.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        registrationPlateColumn.setCellValueFactory(cellData -> cellData.getValue().registrationPlateProperty());
        trunkSpaceHeightColumn.setCellValueFactory(cellData -> cellData.getValue().trunkSpaceHeightProperty().asObject());
        trunkSpaceWidthColumn.setCellValueFactory(cellData -> cellData.getValue().trunkSpaceWidthProperty().asObject());
        carryingCapacityColumn.setCellValueFactory(cellData -> cellData.getValue().carryingCapacityProperty().asObject());
        pricePerDayColumn.setCellValueFactory(cellData -> cellData.getValue().pricePerDayProperty().asObject());

        vehicleRepository = new VehicleRepository();
        prepareTrailers();
    }

    private void prepareTrailers() {
        List<Trailer> trailers = vehicleRepository.loadTrailers();
        trailerData.clear();
        for (Trailer trailer : trailers) {
            trailerData.add(new TrailerViewModel(trailer));
        }
        trailerTable.setItems(trailerData);
    }

    @FXML
    private void addTrailer() {
        openTrailerDialog(null);
    }

    @FXML
    private void removeTrailer() {
        TrailerViewModel selectedTrailer = trailerTable.getSelectionModel().getSelectedItem();
        if (selectedTrailer != null) {
            trailerData.remove(selectedTrailer);
        }
    }

    private void openTrailerDialog(TrailerViewModel trailerViewModel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/addEdit/AddTrailerView.fxml"));
            Parent root = loader.load();

            AddTrailerViewController controller = loader.getController();
            controller.setViewModel(trailerData, trailerViewModel);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(trailerViewModel == null ? "Add Trailer" : "Edit Trailer");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void localizeVehicle() {
        TrailerViewModel selectedCar = trailerTable.getSelectionModel().getSelectedItem();
        PopUpUtil.popUpInfo("Vehicle Localization", selectedCar.getRegistrationPlate() +
                " location is: "+ ClientRequestHandler.getResponse("getCoordinates"));
    }

    @FXML
    private void resetVehicleLocation() {
        TrailerViewModel selectedCar = trailerTable.getSelectionModel().getSelectedItem();
        PopUpUtil.popUpInfo("Vehicle Localization", selectedCar.getRegistrationPlate() +
                " location is: "+ClientRequestHandler.getResponse("resetCoordinates"));
    }

    @FXML
    private void editTrailer() {
        TrailerViewModel selectedTrailer = trailerTable.getSelectionModel().getSelectedItem();
        if (selectedTrailer != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/addEdit/EditTrailer.fxml"));
                Parent root = loader.load();

                EditTrailerController controller = loader.getController();
                controller.setTrailer(selectedTrailer);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Edit Trailer");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                prepareTrailers();
                trailerTable.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
