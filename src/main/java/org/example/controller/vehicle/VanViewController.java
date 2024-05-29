package org.example.controller.vehicle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.controller.vehicle.addEdit.AddEditVanViewController;
import org.example.viewmodel.vehicle.VanViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class VanViewController {
    @FXML private TableView<VanViewModel> vanTable;
    @FXML private TableColumn<VanViewModel, String> makeColumn;
    @FXML private TableColumn<VanViewModel, String> brandColumn;
    @FXML private TableColumn<VanViewModel, String> registrationPlateColumn;
    @FXML private TableColumn<VanViewModel, Integer> trunkSpaceHeightColumn;
    @FXML private TableColumn<VanViewModel, Integer> trunkSpaceWidthColumn;
    @FXML private TableColumn<VanViewModel, Integer> carryingCapacityColumn;
    @FXML private TableColumn<VanViewModel, Integer> hpColumn;
    @FXML private TableColumn<VanViewModel, Integer> pricePerDayColumn;

    private ObservableList<VanViewModel> vans;

    public void initialize() {
        vans = FXCollections.observableArrayList();

        makeColumn.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        registrationPlateColumn.setCellValueFactory(cellData -> cellData.getValue().registrationPlateProperty());
        trunkSpaceHeightColumn.setCellValueFactory(cellData -> cellData.getValue().trunkSpaceHeightProperty().asObject());
        trunkSpaceWidthColumn.setCellValueFactory(cellData -> cellData.getValue().trunkSpaceWidthProperty().asObject());
        carryingCapacityColumn.setCellValueFactory(cellData -> cellData.getValue().carryingCapacityProperty().asObject());
        hpColumn.setCellValueFactory(cellData -> cellData.getValue().hpProperty().asObject());
        pricePerDayColumn.setCellValueFactory(cellData -> cellData.getValue().pricePerDayProperty().asObject());

        vanTable.setItems(vans);
    }

    @FXML
    private void addVan() {
        openVanDialog(null);
    }

    @FXML
    private void removeVan() {
        VanViewModel selectedVan = vanTable.getSelectionModel().getSelectedItem();
        if (selectedVan != null) {
            vans.remove(selectedVan);
        }
    }

    private void openVanDialog(VanViewModel vanViewModel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vehicle/addEdit/AddEditVanView.fxml"));
            Parent root = loader.load();

            AddEditVanViewController controller = loader.getController();
            controller.setViewModel(vans, vanViewModel);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(vanViewModel == null ? "Add Van" : "Edit Van");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
