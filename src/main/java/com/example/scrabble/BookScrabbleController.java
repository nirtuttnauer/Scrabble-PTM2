package com.example.scrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.HostModel;
import model.iModel;
import vm.HostViewModel;

public class BookScrabbleController {
    @FXML
    private Label welcomeText;

    private HostViewModel viewModel;

    public void initialize() {
        HostModel model = new HostModel();
        viewModel = new HostViewModel(model);
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
