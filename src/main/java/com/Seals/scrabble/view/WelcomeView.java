package com.Seals.scrabble.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class WelcomeView extends View implements IView {
    @FXML
    public TextField nicknameField;

    public WelcomeView() {
//        bindVM();
    }

    public void bindVM() {
        if (vm != null) {
            nicknameField.textProperty().bindBidirectional(vm.nicknameProperty());
        } else {
            System.out.println("vm is null");
        }
    }

    @FXML
    public void onNameSubmitButtonClick() throws IOException {
        String nickname = nicknameField.getText().trim();
        onMenuButtonClick();
        vm.setNickname(nickname);
        System.out.println("Nickname set to: " + vm.getNickname());
    }
}
