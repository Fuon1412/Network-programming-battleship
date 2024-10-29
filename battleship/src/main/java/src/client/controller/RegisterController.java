package src.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import src.client.GameClient;
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
    private void initialize() {
        registerSubmit.setOnAction(event -> handleRegister());
        switchToLogin.setOnAction(event -> switchToLogin());
    }

    private void handleRegister() {
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        GameClient.sendRegister(name, username, password);
    }

    private void switchToLogin() {
        // Chuyển đến giao diện đăng nhập
        // Bạn có thể sử dụng SceneSwitcher hoặc bất kỳ phương thức nào bạn đã thiết lập
    }
}
