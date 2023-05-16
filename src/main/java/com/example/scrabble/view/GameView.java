package com.example.scrabble.view;

import com.example.scrabble.App;
import com.example.scrabble.vm.generics.HostVM;
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
        App.setScene("LeaderboardView");
        HostVM hvm = (HostVM) View.vm;
        hvm.closeServer();
    }
}
