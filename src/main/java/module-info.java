module com.example.service {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.service to javafx.fxml;
    exports com.example.service;
}