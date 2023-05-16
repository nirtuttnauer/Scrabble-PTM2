package com.example.scrabble.vm.view_vm;

import com.example.scrabble.App;
import com.example.scrabble.vm.generics.IVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class vmSettingsView extends vmView implements Observer, IVM {
    @FXML
    private Label nickname;

    @FXML
    private Label title;

    public vmSettingsView() {
        super();
    }



    @Override
    public void update(Observable o, Object arg) {

    }

    public void onRunTestsButtonClick(ActionEvent event) {

    }

    @FXML
    public void onChangeNameButtonClick(ActionEvent event) throws IOException {
         App.setScene("welcome-view.fxml");
    }
}
