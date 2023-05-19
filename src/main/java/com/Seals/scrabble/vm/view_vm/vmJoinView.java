package com.Seals.scrabble.vm.view_vm;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.vm.generics.IVM;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class vmJoinView extends vmView implements Observer, IVM {
    @FXML
    private Label nickname;

    public vmJoinView() {
        super();
    }



    @Override
    public void update(Observable o, Object arg) {

    }

    public void onGameJoinButtonClick() throws IOException {
         Main.setScene("lobby-view.fxml");
    }


}
