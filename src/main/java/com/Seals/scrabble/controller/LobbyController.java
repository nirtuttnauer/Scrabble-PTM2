package com.Seals.scrabble.controller;

import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public class LobbyController  implements Observer,iController {
    // Add your lobby-related logic and event handlers here

    @FXML
    public void initialize() {
        // Initialize the lobby view
    }



    public void onGameButtonClick(ActionEvent event) {

        setScene("GameView");
        ViewModel.getSharedInstance().startGame();

    }



    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ViewModel){
            System.out.println("Update from" + this.getClass().toString());
        }
    }

    @Override
    public void onMenuButtonClick() throws IOException {
        setScene("MenuView");
    }

    @Override
    public void onExitButtonClick() throws IOException {
    
    }
}