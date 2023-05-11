package com.example.scrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BookScrabbleController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Lama AVIV Kaha Kaved?!");
    }

    @FXML
    protected void onJoinButtonClick() {
        welcomeText.setText("Lama");
    }

}