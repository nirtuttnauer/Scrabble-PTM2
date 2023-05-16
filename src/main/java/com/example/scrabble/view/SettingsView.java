package com.example.scrabble.view;

import com.example.scrabble.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class SettingsView extends View implements Observer, IView{
    @FXML
    private Label nickname;

    @FXML
    private Label title;




    @Override
    public void update(Observable o, Object arg) {

    }

    public void onRunTestsButtonClick(ActionEvent event) {

    }

    @FXML
    public void onChangeNameButtonClick(ActionEvent event) throws IOException {
         Main.setScene("WelcomeView");
    }
}
