module com.example.scrabble {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.example.scrabble to javafx.fxml;
    exports com.example.scrabble;
    exports com.example.scrabble.view;
    opens com.example.scrabble.view to javafx.fxml;
    exports com.example.scrabble.factories;
    opens com.example.scrabble.factories to javafx.fxml;
}
