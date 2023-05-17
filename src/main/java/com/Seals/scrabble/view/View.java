package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.vm.generics.VM;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class View implements IView, Initializable {
    protected static VM vm;

    public View() {
        if (vm == null) vm = new VM();
    }

    public void onMenuButtonClick() throws IOException {
        Main.setScene("MenuView");
    }

    public void onExitButtonClick() throws IOException {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization logic goes here
    }
}
