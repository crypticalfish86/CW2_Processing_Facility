module com.example.coursework_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.coursework_2 to javafx.fxml;
    exports com.example.coursework_2;
}