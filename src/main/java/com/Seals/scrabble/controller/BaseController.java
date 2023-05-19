package com.Seals.scrabble.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import static com.Seals.scrabble.factories.SceneFactory.setScene;


public abstract class BaseController implements Initializable, Observer,iController {



    @FXML
    protected Button menuButton;

    public BaseController() {

    }


    @FXML
    public void onMenuButtonClick() throws IOException {
        setScene("MenuView");
    }

    // Other common methods...


    @Override
    public void update(Observable o, Object arg) {

    }

    public void onSwitchButtonClick(ActionEvent event) {

    }
}
