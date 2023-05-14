package com.example.scrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.scrabble.model.HostModel;
import com.example.scrabble.vm.HostVM;

public class BookScrabbleController {
    @FXML
    private Label welcomeText;

    private HostVM viewModel;

    public void initialize() {
        HostModel model = new HostModel();
        viewModel = new HostVM(model);
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
}
