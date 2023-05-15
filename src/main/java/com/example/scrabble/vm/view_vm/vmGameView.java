package com.example.scrabble.vm.view_vm;

import com.example.scrabble.App;
import com.example.scrabble.vm.generics.IVM;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class vmGameView extends vmView implements Observer, IVM {
    public vmGameView() {
        super();
    }


    @Override
    public void update(Observable o, Object arg) {

    }

    @FXML
    public void onFinishButtonClick() throws IOException {
        App.setScene("leaderboard-view.fxml");
    }
}
