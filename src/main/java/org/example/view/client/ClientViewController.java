package org.example.view.client;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.viewmodel.client.ClientViewModel;

public class ClientViewController {

    @FXML
    private ListView<String> vehicleListView;
    @FXML
    private ListView<String> bookingListView;

    private ClientViewModel viewModel;

    public void setViewModel(ClientViewModel viewModel) {
        this.viewModel = viewModel;

        vehicleListView.setItems(viewModel.getAvailableVehicles());
        bookingListView.setItems(viewModel.getBookings());
    }

    @FXML
    private void rentVehicle() {
        String selectedVehicle = vehicleListView.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            viewModel.rentVehicle(selectedVehicle);
        }
    }
}
