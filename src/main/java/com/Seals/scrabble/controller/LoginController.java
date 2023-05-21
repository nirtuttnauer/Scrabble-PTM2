package com.Seals.scrabble.controller;

import com.Seals.scrabble.factories.SceneFactory;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements iController, Initializable {
    private ViewModel viewModel;

    @FXML
    private TextField nameField;

    @FXML
    private Button submitButton;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onMenuButtonClick() throws IOException {
        // Implement your logic to switch to the menu view
        SceneFactory.setScene("MenuView");
    }

    @Override
    public void onExitButtonClick() throws IOException {
        System.exit(0);
    }

    public void onSubmitButtonClick(ActionEvent event) {
        System.out.println("Submit button clicked");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
                // Initialize the controller
        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            viewModel.setNickname(name);
            try {
                onMenuButtonClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
