module com.example.scrabble {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.Seals.scrabble to javafx.fxml;
    exports com.Seals.scrabble;
    exports com.Seals.scrabble.controller;
    opens com.Seals.scrabble.controller to javafx.fxml;
    exports com.Seals.scrabble.factories;
    opens com.Seals.scrabble.factories to javafx.fxml;
    exports com.Seals.scrabble.factories.command;
    opens com.Seals.scrabble.factories.command to javafx.fxml;
}
