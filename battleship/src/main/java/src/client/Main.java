package src.client;
/*
 * Lớp Main chứa hàm main của client, khi chạy ứng dụng chúng ta sẽ 
 * chạy hàm này cùng với hàm GameServer bên server
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //Phương thức start để set up các scene khởi đầu khi chạy ứng dụng
    @Override
    public void start(Stage primaryStage) {
        try {
            //Load file fxml vào, tạm thời đang để login scene, sẽ đổi lại thành start scene sau
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginScene.fxml")); 
            Parent root = loader.load();
            primaryStage.setTitle("Battleship v1.0");
            primaryStage.setScene(new Scene(root, 1078, 686));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Phương thức main, ấn run để chạy ứng dụng
    public static void main(String[] args) {
        launch(args);
    }
}
