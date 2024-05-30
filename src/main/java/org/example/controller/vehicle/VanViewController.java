package org.example.controller.vehicle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.controller.vehicle.addEdit.AddVanViewController;
import org.example.controller.vehicle.addEdit.EditVanController;
import org.example.model.vehicle.Van;
import org.example.repository.vehicle.VehicleRepository;
import org.example.viewmodel.vehicle.VanViewModel;

import java.io.IOException;
import java.util.List;

public class VanViewController {
    @FXML
    private TableView<VanViewModel> vanTable;
    @FXML
    private TableColumn<VanViewModel, String> makeColumn;
    @FXML
    private TableColumn<VanViewModel, String> brandColumn;
    @FXML
    private TableColumn<VanViewModel, String> registrationPlateColumn;
    @FXML
    private TableColumn<VanViewModel, Integer> trunkSpaceHeightColumn;
    @FXML
    private TableColumn<VanViewModel, Integer> trunkSpaceWidthColumn;
    @FXML
    private TableColumn<VanViewModel, Integer> carryingCapacityColumn;
    @FXML
    private TableColumn<VanViewModel, Integer> hpColumn;
    @FXML
    private TableColumn<VanViewModel, Integer> pricePerDayColumn;
    private ObservableList<VanViewModel> vansData;
    VehicleRepository vehicleRepository;

    public void initialize() {
        vansData = FXCollections.observableArrayList();

        makeColumn.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        registrationPlateColumn.setCellValueFactory(cellData -> cellData.getValue().registrationPlateProperty());
        trunkSpaceHeightColumn.setCellValueFactory(cellData -> cellData.getValue().trunkSpaceHeightProperty().asObject());
        trunkSpaceWidthColumn.setCellValueFactory(cellData -> cellData.getValue().trunkSpaceWidthProperty().asObject());
        carryingCapacityColumn.setCellValueFactory(cellData -> cellData.getValue().carryingCapacityProperty().asObject());
        hpColumn.setCellValueFactory(cellData -> cellData.getValue().hpProperty().asObject());
        pricePerDayColumn.setCellValueFactory(cellData -> cellData.getValue().pricePerDayProperty().asObject());

        vehicleRepository = new VehicleRepository();
        prepareVans();
    }

    private void prepareVans() {
        List<Van> vans = vehicleRepository.loadVans();
        vansData.clear();
        for (Van van : vans) {
            vansData.add(new VanViewModel(van));
        }
        vanTable.setItems(vansData);
    }

    @FXML
    private void addVan() {
        openVanDialog(null);
    }

    @FXML
    private void removeVan() {
        VanViewModel selectedVan = vanTable.getSelectionModel().getSelectedItem();
        if (selectedVan != null) {
            vansData.remove(selectedVan);
        }
    }

    private void openVanDialog(VanViewModel vanViewModel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/addEdit/AddVanView.fxml"));
            Parent root = loader.load();

            AddVanViewController controller = loader.getController();
            controller.setViewModel(vansData, vanViewModel);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(vanViewModel == null ? "Add Van" : "Edit Van");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editVan() {
        VanViewModel selectedVan = vanTable.getSelectionModel().getSelectedItem();
        if (selectedVan != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/addEdit/EditVan.fxml"));
                Parent root = loader.load();

                EditVanController controller = loader.getController();
                controller.setVan(selectedVan);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Edit Van");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                prepareVans();
                vanTable.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
