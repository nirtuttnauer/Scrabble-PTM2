package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class LobbyView extends View{

    @FXML
    public void onGameButtonClick() throws IOException {
        Main.setScene("GameView");
    }
}
