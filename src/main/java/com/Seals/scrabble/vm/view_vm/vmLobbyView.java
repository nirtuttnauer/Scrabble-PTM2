package com.Seals.scrabble.vm.view_vm;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.vm.generics.IVM;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class vmLobbyView extends vmView implements Observer, IVM {
    public vmLobbyView() {
        super();
    }

    @Override
    public void update(Observable o, Object arg) {

    }


    @FXML
    public void onGameButtonClick() throws IOException {
        Main.setScene("game-view.fxml");
    }
}