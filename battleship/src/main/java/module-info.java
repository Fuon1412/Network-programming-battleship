module com.example {
    // Cái này để làm méo gì t cũng k rõ, nhưng nó cần thiết để chạy JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires transitive javafx.graphics;
    requires com.google.gson;
    requires javafx.base;
    requires java.desktop;
    // Export packages to make them accessible
    exports src.server;
    exports src.client;
    exports src.client.controller;
    exports src.server.models;
    // Open packages for reflection (e.g., for FXML loading)
    opens src.client.controller to javafx.fxml;
    opens src.server.models to com.google.gson;
}

