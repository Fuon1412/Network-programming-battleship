package src.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import src.client.GameClient;

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
    private void initialize() {
        loginSubmit.setOnAction(event -> handleLogin());
        switchToRegister.setOnAction(event -> switchToRegister());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        GameClient.sendLogin(username, password);
    }

    private void switchToRegister() {
        // Chuyển đến giao diện đăng ký
        // Bạn có thể sử dụng SceneSwitcher hoặc bất kỳ phương thức nào bạn đã thiết lập
    }
}
