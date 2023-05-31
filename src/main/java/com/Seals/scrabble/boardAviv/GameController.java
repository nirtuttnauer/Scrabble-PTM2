package com.Seals.scrabble.boardAviv;

import com.Seals.scrabble.controller.iController;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class GameController  implements Initializable, Observer , iController {
    // Add your game-related logic and event handlers here
@FXML
BoardDisplayer boardDisplayer;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the game view
    }



    public void onFinishButtonClick(ActionEvent event) {
    }

    public void onMenuButtonClick(ActionEvent event) {
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ViewModel){
            System.out.println("Update from" + this.getClass().toString());
        }
    }

    @Override
    public void onMenuButtonClick() throws IOException {

    }

    @Override
    public void onExitButtonClick() throws IOException {

    }
}