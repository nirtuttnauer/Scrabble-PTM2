package com.example.scrabble.view;

import com.example.scrabble.App;
import com.example.scrabble.model.GuestModel;
import com.example.scrabble.vm.generics.GuestVM;
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
         App.setScene("LobbyView");
    }


}
