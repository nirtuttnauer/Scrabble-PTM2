package com.Seals.scrabble.controller;

import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuController implements iController{
    private ViewModel viewModel;

    @FXML
    private Label title;

    @FXML
    private Label nickname;

    @FXML
    private Button hostButton;

    @FXML
    private Button joinButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button exitButton;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void initialize() {
        // Initialize the controller
//        hostButton.setOnAction(event -> viewModel.hostGame());
//        joinButton.setOnAction(event -> viewModel.joinGame());
//        settingsButton.setOnAction(event -> viewModel.openSettings());
//        exitButton.setOnAction(event -> viewModel.exit());
    }

    public void onExitButtonClick(ActionEvent event) {
    }

    public void onSettingButtonClick(ActionEvent event) {
    }

    public void onJoinButtonClick(ActionEvent event) {
    }

    public void onHostButtonClick(ActionEvent event) {
    }

        @Override
        public void onMenuButtonClick() throws IOException {

        }

        @Override
        public void onExitButtonClick() throws IOException {

        }


}
