package com.Seals.scrabble.controller;

import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable, Observer {
    // Add your leaderboard-related logic and event handlers here

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the leaderboard view
    }


    public void onMenuButtonClick(ActionEvent event) {
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ViewModel){

        }
    }
}
