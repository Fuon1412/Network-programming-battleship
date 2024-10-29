module com.example {
    // Cái này để làm méo gì t cũng k rõ, nhưng nó cần thiết để chạy JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires transitive javafx.graphics;
    requires com.google.gson;
    
    opens src.client.controller to javafx.fxml;
    exports src.client;
}
