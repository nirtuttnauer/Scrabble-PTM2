package com.example.scrabble.view;

import com.example.scrabble.App;
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
        App.setScene("GameView");
    }
}
