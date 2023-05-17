package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.vm.HostVM;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuView extends View {
    @FXML
    private Label nickname;

    public MenuView() {
//        bindVM();
    }

    public void bindVM() {
        if (vm != null) {
            nickname.textProperty().bind(((HostVM) vm).vm_nickname);
        } else {
            System.out.println("vm is null");
        }
    }

    @FXML
    protected void onJoinButtonClick() throws IOException {
        Main.setScene("JoinView");
    }

    public void onSettingButtonClick() throws IOException {
        Main.setScene("SettingsView");
    }

    public void onHostButtonClick() throws IOException {
        Main.setScene("HostView");
        this.vm = new HostVM(vm);
//        bindVM();
    }
}
