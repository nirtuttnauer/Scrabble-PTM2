package com.example.scrabble;

import com.example.scrabble.model.StartModel;
import com.example.scrabble.model.iModel;
import com.example.scrabble.vm.StartVM;
import com.example.scrabble.vm.iVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.scrabble.model.HostModel;
import com.example.scrabble.vm.HostVM;

public class BookScrabbleController {
    @FXML
    private Label welcomeText;

    private iVM viewModel;

    public void initialize() {
        iModel model = new StartModel();
        viewModel = new StartVM(model);
        welcomeText.textProperty().bind(viewModel.welcomeMessageProperty());
    }

    @FXML
    protected void onHelloButtonClick() {
        viewModel.onHelloButtonClick();
    }

    @FXML
    protected void onJoinButtonClick() {
        viewModel.onJoinButtonClick();
    }

    public void onSettingButtonClick() {
        viewModel.onSettingButtonClick();
    }
}
