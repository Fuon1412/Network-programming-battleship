package src.client.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import src.client.GameClient;
import src.client.Main;

public class StartController {
    @FXML
    private Button startGame;

    @FXML
    private Button exitGame;

    @FXML
    private void initialize() {
        startGame.setOnAction(event -> {
            try {
                startGame(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        exitGame.setOnAction(event -> {
            try {
                exitGame(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void startGame(ActionEvent event) throws IOException {
        Main.switchScene(event, "/client/view/loginScene.fxml");
    }

    private void exitGame(ActionEvent event) throws IOException {
        GameClient.getInstance().sendExit();
        System.exit(0);
    }
}
