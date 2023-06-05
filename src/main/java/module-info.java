module com.example.lab.Apps {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;


    opens com.example.lab8.Apps to javafx.fxml;
    exports com.example.lab8.Apps;
    exports com.example.lab8.Base;
    exports com.example.lab8.Animation;
}