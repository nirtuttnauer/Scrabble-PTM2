package com.example.scrabble.view;

import com.example.scrabble.Main;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class LobbyView extends View implements Observer, IView{

    @Override
    public void update(Observable o, Object arg) {

    }


    @FXML
    public void onGameButtonClick() throws IOException {
        Main.setScene("GameView");
    }
}
