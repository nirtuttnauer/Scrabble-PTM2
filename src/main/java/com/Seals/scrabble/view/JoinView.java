package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class JoinView extends View implements Observer, IView{
    @FXML
    private Label nickname;





    @Override
    public void update(Observable o, Object arg) {

    }

    public void onGameJoinButtonClick() throws IOException {
         Main.setScene("LobbyView");
    }


}
