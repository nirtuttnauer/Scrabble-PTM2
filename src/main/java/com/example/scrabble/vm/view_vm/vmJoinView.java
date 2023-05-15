package com.example.scrabble.vm.view_vm;

import com.example.scrabble.App;
import com.example.scrabble.vm.generics.IVM;
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
         App.setScene("lobby-view.fxml");
    }


}
