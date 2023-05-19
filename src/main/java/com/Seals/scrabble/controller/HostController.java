package com.Seals.scrabble.controller;

import com.Seals.scrabble.viewmodel.ViewModel;
import com.Seals.scrabble.viewmodel.hViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HostController extends MainController implements Initializable ,iController{
    // Add your host-related logic and event handlers here

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the host view
    }

    @Override
    public void onSwitchButtonClick(ActionEvent event) {

    }

    public void onLobbyButtonClick(ActionEvent event) {
    }

    public void onTestDMServerConnection(ActionEvent event) {
        if (getVm() instanceof ViewModel)
            setVM(new hViewModel(getVm()));
        getVm().testDMServerConnection();
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
