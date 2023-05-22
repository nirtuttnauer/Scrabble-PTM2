package com.Seals.scrabble.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class HostController implements Initializable, Observer {
    // Add your host-related logic and event handlers here

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the host view
    }


    public void onLobbyButtonClick(ActionEvent event) {
    }

    public void onTestDMServerConnection(ActionEvent event) {
    }

    public void onMenuButtonClick(ActionEvent event) {
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update from" + this.getClass().toString());
    }
}
