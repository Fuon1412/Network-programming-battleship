package src.client.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import src.client.GameClient;
import src.client.Main;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Hyperlink switchToRegister;

    @FXML
    private Button loginSubmit;

    @FXML
    private Button returnStartScene;

    @FXML
    private void initialize() {
        loginSubmit.setOnAction(event -> handleLogin());
        switchToRegister.setOnAction(event -> {
            try {
                switchToRegister(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        returnStartScene.setOnAction(event -> {
            try {
                returnStartScene(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Gửi request đến server để đăng nhập
        GameClient.getInstance().sendLogin(username, password);
    }

    private void switchToRegister(ActionEvent event) throws IOException {
        Main.switchScene(event, "/client/view/registerScene.fxml");
    }

    private void returnStartScene(ActionEvent event) throws IOException {
        Main.switchScene(event, "/client/view/startScene.fxml");
    }
}
