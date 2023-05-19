package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.vm.generics.HostVM;
import com.Seals.scrabble.vm.generics.VM;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MenuView extends View implements Observer, IView {
    @FXML
    public Label nickname;

    @FXML
    public void bindVM() {
        nickname = new Label("Omer");
        nickname.textProperty().bind(vm.vm_nickname);
    }

    public MenuView() {
        bindVM();
    }


    @Override
    public void update(Observable o, Object arg) {

    }

    @FXML
    protected void onJoinButtonClick() throws IOException {
        Main.setScene("JoinView");
        setVm(new VM());
    }

    @FXML
    public void onSettingButtonClick() throws IOException {
        Main.setScene("SettingsView");
    }

    @FXML
    public void onHostButtonClick() throws IOException {
        Main.setScene("HostView");
        setVm(new HostVM());
    }

}
