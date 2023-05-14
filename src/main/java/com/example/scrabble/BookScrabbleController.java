package com.example.scrabble;

import com.example.scrabble.model.GuestModel;
import com.example.scrabble.model.HostModel;
import com.example.scrabble.model.Model;
import com.example.scrabble.vm.GuestVM;
import com.example.scrabble.vm.HostVM;
import com.example.scrabble.vm.VM;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class BookScrabbleController implements Observable {


    private VM vm;

    private SceneController sc;

    @FXML
    private Label nickname;
    @FXML
    public TextField nicknameField;
    @FXML
    private Label title;

    @FXML
    public void initialize() {
        Model model = new Model();
        vm = new VM(model);
        sc = new SceneController();
//        bindViewModel();
    }

//    private void bindViewModel() {
//        nickname.textProperty().bind(vm.nicknameProperty().get());
//    }

    @FXML
    protected void onJoinButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "join-view.fxml");
        setVm(new GuestVM(new GuestModel()));
    }

    @FXML
    public void onSettingButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "settings-view.fxml");
    }

    @FXML
    public void onHostButtonClick(ActionEvent event) throws IOException {
        sc.setScene(event, "host-view.fxml");
        vm = new HostVM(new HostModel());
        vm.serverStart();
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
        onMenuButtonClick(event);
        if (!nickname.isEmpty()) {
            vm.setNickname(nickname);
            System.out.println("Nickname set to: " + vm.getNickname());
        }
    }

    public void setVm(VM vm) {
        this.vm = vm;
    }

    @FXML
    public void onExitButtonClick(ActionEvent event) {
        System.exit(0);
    }

    public void onGameJoinButtonClick(ActionEvent event) {
    }

    public void onRunTestsButtonClick(ActionEvent event) {
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}
