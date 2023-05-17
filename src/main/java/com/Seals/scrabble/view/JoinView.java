package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class JoinView extends View {


    public void onGameJoinButtonClick(ActionEvent event) throws IOException {
        Main.setScene("GameView");

    }
}
