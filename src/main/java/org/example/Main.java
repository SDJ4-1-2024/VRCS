package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controller.LoginViewController;
import org.example.viewmodel.login.LoginViewModel;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/LoginView.fxml"));
        Parent root = loader.load();

        LoginViewController loginController = loader.getController();
        loginController.setViewModel(new LoginViewModel());
        
        primaryStage.setTitle("Login/Registration");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
