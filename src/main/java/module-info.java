module com.example.scrabble {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scrabble to javafx.fxml;
    exports com.example.scrabble;
    exports com.example.scrabble.model;
    opens com.example.scrabble.model to javafx.fxml;
    opens com.example.scrabble.modelAviv to javafx.fxml;
}