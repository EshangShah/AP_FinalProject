module com.example.snakesandladder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakesandladder to javafx.fxml;
    exports com.example.snakesandladder;
}