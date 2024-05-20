package org.example.view.admin;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.viewmodel.admin.AdminViewModel;

public class AdminViewController {

    @FXML
    private ListView<String> vehicleListView;
    @FXML
    private ListView<String> bookingListView;

    private AdminViewModel viewModel;

    public void setViewModel(AdminViewModel viewModel) {
        this.viewModel = viewModel;

        vehicleListView.setItems(viewModel.getVehicles());
        bookingListView.setItems(viewModel.getBookings());
    }

    @FXML
    private void addVehicle() {
        // Show add vehicle dialog and get vehicle details
        viewModel.addVehicle("New Vehicle");
    }

    @FXML
    private void removeVehicle() {
        String selectedVehicle = vehicleListView.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            viewModel.removeVehicle(selectedVehicle);
        }
    }

    @FXML
    private void removeBooking() {
        String selectedBooking = bookingListView.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            viewModel.removeBooking(selectedBooking);
        }
    }
}
