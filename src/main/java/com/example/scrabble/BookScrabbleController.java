package com.example.scrabble;

import com.example.scrabble.model.GuestModel;
import com.example.scrabble.model.HostModel;
import com.example.scrabble.model.StartModel;
import com.example.scrabble.model.iModel;
import com.example.scrabble.vm.GuestVM;
import com.example.scrabble.vm.HostVM;
import com.example.scrabble.vm.VM;
import com.example.scrabble.vm.iVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class BookScrabbleController {
    @FXML
    String nicknameString;
    @FXML
    public TextField nickname;
    @FXML
    private Label MainTitle;
    @FXML
    private iVM viewModel;
    @FXML
    private SceneController sc = new SceneController();

    @FXML
    public void initialize() {
        iModel model = new StartModel();
        viewModel = new VM(model);
        MainTitle.setText("BookScrabble");
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
        MainTitle.setText("Welcome, " + nicknameString + "!");
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
        nicknameString = nickname.getText();
        onMenuButtonClick(event);
        System.out.println(MainTitle.getText());
    }

    public void setViewModel(iVM viewModel) {
        this.viewModel = viewModel;
    }

    public void onExitButtonClick(ActionEvent event) {
        System.exit(0);
    }
}
