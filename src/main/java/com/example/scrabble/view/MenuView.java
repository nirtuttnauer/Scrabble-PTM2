package com.example.scrabble.view;

import com.example.scrabble.App;
import com.example.scrabble.model.GuestModel;
import com.example.scrabble.model.HostModel;
import com.example.scrabble.vm.generics.GuestVM;
import com.example.scrabble.vm.generics.HostVM;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MenuView extends View implements Observer, IView {
    @FXML
    public Label nickname;

    @FXML
    public void bindVM() {
        nickname = new Label("Omer");
        nickname.textProperty().bind(View.vm.vm_nickname);
    }

    public MenuView() {
        bindVM();
    }


    @Override
    public void update(Observable o, Object arg) {

    }

    @FXML
    protected void onJoinButtonClick() throws IOException {
        App.setScene("JoinView");
        View.setVm(new GuestVM(new GuestModel()));
    }

    @FXML
    public void onSettingButtonClick() throws IOException {
        App.setScene("SettingsView");
    }

    @FXML
    public void onHostButtonClick() throws IOException {
        App.setScene("HostView");
        View.setVm(new HostVM(new HostModel()));
    }

}
