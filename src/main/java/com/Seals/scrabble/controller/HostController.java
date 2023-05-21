package com.Seals.scrabble.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HostController implements Initializable ,iController{
    // Add your host-related logic and event handlers here

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the host view
    }
    public void onLobbyButtonClick(ActionEvent event) {
    }

    public void onTestDMServerConnection(ActionEvent event) {
//        if (ViewModelFactory.getVm() instanceof ViewModel)
//            ViewModelFactory.setVM(new hViewModel(getVm()));
//        getVm().testDMServerConnection();
    }

    public void onMenuButtonClick(ActionEvent event) {
    }

    @Override
    public void onMenuButtonClick() throws IOException {

    }

    @Override
    public void onExitButtonClick() throws IOException {

    }
}
