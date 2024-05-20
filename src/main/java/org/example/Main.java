package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.viewmodel.admin.AdminViewModel;
import org.example.viewmodel.client.ClientViewModel;
import org.example.view.admin.AdminViewController;
import org.example.view.client.ClientViewController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up client view
        FXMLLoader clientLoader = new FXMLLoader(getClass().getResource("/client/ClientView.fxml"));
        VBox clientRoot = clientLoader.load();
        ClientViewController clientController = clientLoader.getController();
        ClientViewModel clientViewModel = new ClientViewModel();
        clientController.setViewModel(clientViewModel);

        // Set up admin view
        FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/admin/AdminView.fxml"));
        VBox adminRoot = adminLoader.load();
        AdminViewController adminController = adminLoader.getController();
        AdminViewModel adminViewModel = new AdminViewModel();
        adminController.setViewModel(adminViewModel);

        // Create separate stages for client and admin views
        Stage clientStage = new Stage();
        clientStage.setTitle("Client View");
        clientStage.setScene(new Scene(clientRoot));
        clientStage.show();

        Stage adminStage = new Stage();
        adminStage.setTitle("Admin View");
        adminStage.setScene(new Scene(adminRoot));
        adminStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
