package com.example.scrabble;

import com.example.scrabble.model.GuestModel;
import com.example.scrabble.model.HostModel;
import com.example.scrabble.model.StartModel;
import com.example.scrabble.model.iModel;
import com.example.scrabble.vm.GuestVM;
import com.example.scrabble.vm.HostVM;
import com.example.scrabble.vm.StartVM;
import com.example.scrabble.vm.iVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class BookScrabbleController {
    @FXML
    private Label MainTitle;

    private iVM viewModel;

    private SceneController sc = new SceneController();

    public void initialize() {
        iModel model = new StartModel();
        viewModel = new StartVM(model);
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
    }
    @FXML
    public void onLobbyButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "lobby-view.fxml");
    }

    public void onGameButtonClick(ActionEvent event) throws IOException {
          sc.setScene(event, "game-view.fxml");
    }

    public void onMenuButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "menu-view.fxml");
    }

    public void onFinishButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "leaderboard-view.fxml");
    }
}
