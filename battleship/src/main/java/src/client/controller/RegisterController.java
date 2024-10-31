package src.client.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import src.client.GameClient;
import src.client.Main;

public class RegisterController {
    @FXML
    private TextField nameField;        

    @FXML
    private TextField usernameField;    

    @FXML
    private TextField passwordField;    

    @FXML
    private Hyperlink switchToLogin;   

    @FXML
    private Button registerSubmit;  
    
    @FXML
    private Button returnStartScene;

    @FXML
    private void initialize() {
        registerSubmit.setOnAction(event -> handleRegister());
        switchToLogin.setOnAction(event -> {
            try {
                switchToLogin(event);
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

    private void handleRegister() {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        GameClient.getInstance().sendRegister(name, username, password);
    }

    private void switchToLogin(ActionEvent event) throws IOException {
        Main.switchScene(event, "/client/view/loginScene.fxml");
    }

    private void returnStartScene(ActionEvent event) throws IOException {
        Main.switchScene(event, "/client/view/startScene.fxml");
    }
}
