//package com.Seals.scrabble.controller;
//
//import com.Seals.scrabble.vm.HostVM;
//import com.Seals.scrabble.vm.VM;
//import com.Seals.scrabble.vm.iVM;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.Observable;
//import java.util.Observer;
//import java.util.ResourceBundle;
//
//import static com.Seals.scrabble.factories.SceneFactory.setScene;
//import static com.Seals.scrabble.vm.VM.getSharedInstance;
//import static com.Seals.scrabble.vm.VM.setSharedInstance;
//
//public class Controller implements Initializable, Observer {
//
//    @FXML
//    private TextField nameField;
//
//    @FXML
//    private TextField nicknameField;
//
//    @FXML
//    private Label nickname;
//
//    public Controller() {
//        if (getSharedInstance() == null)
//            setSharedInstance(new VM());
//        this.vm = VM.getSharedInstance();
//        if (this.vm != null)
//            this.vm.addObserver(this);
//    }
//
//    @FXML
//    public void onNameSubmitButtonClick(ActionEvent event) throws IOException {
//        String nickname = nameField.getText().trim();
//
//        if (!nickname.isEmpty()) {
//            vm.setNickname(nickname);
//            this.vm.nicknameProperty().addListener((observable, oldValue, newValue) -> {
//                if (nickname != null) {
//                    this.nickname.setText(newValue);
//                }
//            });
//            setScene("MenuView");
//        }
//    }
//
//    @FXML
//    public void onMenuButtonClick() throws IOException {
//        setScene("MenuView");
//    }
//
//
//
//    @FXML
//    public void onLobbyButtonClick(ActionEvent event) throws IOException {
//        setScene("LobbyView");
//    }
//
//
//
//
//
//    @FXML
//    public void onTestDMServerConnection(ActionEvent event) {
//        if (vm instanceof HostVM) {
//            HostVM hostVM = (HostVM) vm;
//            hostVM.testDMServerConnection();
//        }
//    }
//
//    // Rest of the code
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        vm = VM.getSharedInstance();
//        bindVM();
//    }
//
//    public void bindVM() {
//        if (nickname != null && vm != null) {
//            nickname.textProperty().bind(vm.nicknameProperty());
//        }
//    }
//
//    @Override
//    public void update(Observable o, Object arg) {
//        if (o == vm && nickname != null) {
//            nickname.setText(vm.getNickname());
//        }
//    }
//
//
//
//}
