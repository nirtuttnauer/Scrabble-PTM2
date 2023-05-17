package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.vm.generics.HostVM;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class GameView extends View implements Observer, IView{

    @Override
    public void update(Observable o, Object arg) {

    }

    @FXML
    public void onFinishButtonClick() throws IOException {
        Main.setScene("LeaderboardView");
        HostVM hvm = (HostVM) View.vm;
        hvm.closeServer();
    }
}
