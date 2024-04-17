module com.example.ds_simulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ds_simulator to javafx.fxml;
    exports com.example.ds_simulator;
}