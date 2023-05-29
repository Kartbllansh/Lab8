module com.example.lab.Apps {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab8.Apps to javafx.fxml;
    exports com.example.lab8.Apps;
}