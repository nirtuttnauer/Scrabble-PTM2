package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SettingsView extends View{


    public void onRunTestsButtonClick(ActionEvent event) {

    }

    @FXML
    public void onChangeNameButtonClick(ActionEvent event) throws IOException {
         Main.setScene("WelcomeView");
    }
}
