package com.Seals.scrabble.controller;

import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class JoinController  implements Initializable, Observer {
    // Add your join-related logic and event handlers here

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the join view
    }


    public void onGameJoinButtonClick(ActionEvent event) {
    }

    public void onMenuButtonClick(ActionEvent event) {
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ViewModel) {

        }
    }
}
