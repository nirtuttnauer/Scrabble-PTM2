module com.example.scrabble {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scrabble to javafx.fxml;
    exports com.example.scrabble;
}