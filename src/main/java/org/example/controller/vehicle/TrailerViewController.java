package org.example.controller.vehicle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.controller.vehicle.addEdit.AddEditTrailerViewController;
import org.example.model.vehicle.Trailer;
import org.example.repository.vehicle.VehicleRepository;
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

    public void initialize() {
        trailerData = FXCollections.observableArrayList();
        makeColumn.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        registrationPlateColumn.setCellValueFactory(cellData -> cellData.getValue().registrationPlateProperty());
        trunkSpaceHeightColumn.setCellValueFactory(cellData -> cellData.getValue().trunkSpaceHeightProperty().asObject());
        trunkSpaceWidthColumn.setCellValueFactory(cellData -> cellData.getValue().trunkSpaceWidthProperty().asObject());
        carryingCapacityColumn.setCellValueFactory(cellData -> cellData.getValue().carryingCapacityProperty().asObject());
        pricePerDayColumn.setCellValueFactory(cellData -> cellData.getValue().pricePerDayProperty().asObject());

        VehicleRepository vehicleRepository = new VehicleRepository();
        prepareTrailers(vehicleRepository.loadTrailers());
    }

    private void prepareTrailers(List<Trailer> trailers) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/addEdit/AddEditTrailerView.fxml"));
            Parent root = loader.load();

            AddEditTrailerViewController controller = loader.getController();
            controller.setViewModel(trailerData, trailerViewModel);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(trailerViewModel == null ? "Add Trailer" : "Edit Trailer");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
