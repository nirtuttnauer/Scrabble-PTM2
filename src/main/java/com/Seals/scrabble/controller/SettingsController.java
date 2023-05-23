package com.Seals.scrabble.controller;

import com.Seals.scrabble.factories.SceneFactory;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class SettingsController implements Initializable, Observer, iController {
    // Add your settings-related logic and event handlers here

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the settings view
    }


    public void onChangeNameButtonClick(ActionEvent event) {
        SceneFactory.setScene("LoginView");
    }

    public void onRunTestsButtonClick(ActionEvent event) {
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ViewModel){
            System.out.println("Update from" + this.getClass().toString());
        }
    }

    @Override
    public void onMenuButtonClick() throws IOException {
        SceneFactory.setScene("MenuView");
    }

    @Override
    public void onExitButtonClick() throws IOException {
        Platform.exit();
    }
}
