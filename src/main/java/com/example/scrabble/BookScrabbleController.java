package com.example.scrabble;

import com.example.scrabble.model.GuestModel;
import com.example.scrabble.model.HostModel;
import com.example.scrabble.model.Model;
import com.example.scrabble.model.iModel;
import com.example.scrabble.vm.GuestVM;
import com.example.scrabble.vm.HostVM;
import com.example.scrabble.vm.VM;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class BookScrabbleController {

    @FXML
    public TextField nicknameField;
    @FXML
    private Label title;
    @FXML
    private VM viewModel;
    @FXML
    private SceneController sc;

    @FXML
    private Label nickname;

    @FXML
    public void initialize() {
        Model model = new Model();
        viewModel = new VM(model);
        title.textProperty().bind(viewModel.nicknameProperty());
        if (nickname!=null){
                    nickname.textProperty().bind(viewModel.nicknameProperty());
        }
        sc = new SceneController();
    }

    private void bindViewModel() {
        title.textProperty().bind(viewModel.nicknameProperty());
    }

    @FXML
    protected void onJoinButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "join-view.fxml");
        viewModel = new GuestVM(new GuestModel());
    }

    @FXML
    public void onSettingButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "settings-view.fxml");
    }

    @FXML
    public void onHostButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "host-view.fxml");
        viewModel = new HostVM(new HostModel());
        viewModel.serverStart();
    }

    @FXML
    public void onLobbyButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "lobby-view.fxml");
    }

    @FXML
    public void onGameButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "game-view.fxml");
    }

    @FXML
    public void onMenuButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "menu-view.fxml");
    }

    @FXML
    public void onFinishButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "leaderboard-view.fxml");
    }

    @FXML
    public void onChangeNameButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "welcome-view.fxml");
    }

    @FXML
    public void onNameSubmitButtonClick(ActionEvent event) throws IOException {
        String nickname = nicknameField.getText().trim();
        if (!nickname.isEmpty()) {
            viewModel.setNickname(nickname);
            System.out.println("Nickname set to: " + viewModel.getNickname());
        }
        onMenuButtonClick(event);
    }

    public void setViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    public void onExitButtonClick(ActionEvent event) {
        System.exit(0);
    }
}
