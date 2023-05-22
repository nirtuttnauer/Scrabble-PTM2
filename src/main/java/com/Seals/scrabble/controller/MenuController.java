package com.Seals.scrabble.controller;

import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MenuController  implements iController, Observer {
    private ViewModel viewModel;

    @FXML
    private Label title;

    @FXML
    private Label nickname;

    @FXML
    private Button hostBtn;

    @FXML
    private Button joinBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button exitBtn;

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addObserver(this);
        System.out.println(" This Observer added from" + this.getClass().toString() + "to " + viewModel.getClass().toString());
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


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ViewModel){
            System.out.println("Update from" + this.getClass().toString());
        }
    }
}
