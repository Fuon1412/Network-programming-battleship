package src.client;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            //Khỏi tạo client, tạo 1 luồng mới để lắng nghe server
            GameClient.getInstance().start();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/startScene.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Battleship v1.0");
            primaryStage.setScene(new Scene(root, 1078, 686));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method dùng để chuyển scene, cái hôm qua bị lỗi méo chạy đc, đổi lại cái này chạy ngon rồi
    public static void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(fxmlFile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
