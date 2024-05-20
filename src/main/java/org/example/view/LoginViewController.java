package org.example.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.view.admin.AdminViewController;
import org.example.view.client.ClientViewController;
import org.example.viewmodel.login.LoginViewModel;
import org.example.viewmodel.admin.AdminViewModel;
import org.example.viewmodel.client.ClientViewModel;

import java.io.IOException;

public class LoginViewController {
    @FXML private TextField phoneNumberField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> clientTypeComboBox;
    private LoginViewModel viewModel;

    public void setViewModel(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void handleLogin() {
        String phone = phoneNumberField.getText();
        String password = passwordField.getText();
        if (viewModel.authenticate(phone, password)) {
            if (viewModel.isAdmin(phone)) {
                openAdminView();
            } else {
                openClientView();
            }
        }
    }

    @FXML
    private void handleSignUp() {
        String phone = phoneNumberField.getText();
        String password = passwordField.getText();
        String clientType = clientTypeComboBox.getValue();
        viewModel.signUp(phone, password, clientType);
    }

    private void openClientView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/ClientView.fxml"));
            Parent root = loader.load();
            ClientViewController controller = loader.getController();
            controller.setViewModel(new ClientViewModel());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Client View");
            stage.show();

            closeLoginWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openAdminView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/AdminView.fxml"));
            Parent root = loader.load();
            AdminViewController controller = loader.getController();
            controller.setViewModel(new AdminViewModel());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Admin View");
            stage.show();

            closeLoginWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeLoginWindow() {
        Stage loginStage = (Stage) phoneNumberField.getScene().getWindow();
        loginStage.close();
    }
}
