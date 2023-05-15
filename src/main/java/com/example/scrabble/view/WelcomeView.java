package com.example.scrabble.view;

import com.example.scrabble.model.Model;
import com.example.scrabble.vm.generics.VM;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class WelcomeView extends View implements Observer, IView {
    @FXML
    public TextField nicknameField;



    @Override
    public void update(Observable o, Object arg) {

    }


       @FXML
    public void onNameSubmitButtonClick() throws IOException {
        String nickname = nicknameField.getText().trim();
        onMenuButtonClick();
        if (!nickname.isEmpty()) {
            View.vm.setNickname(nickname);
            System.out.println("Nickname set to: " + View.vm.getNickname());
        }
    }




}
