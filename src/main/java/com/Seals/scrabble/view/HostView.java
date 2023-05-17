package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.vm.generics.HostVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class HostView extends View implements Observer, IView{



    @Override
    public void update(Observable o, Object arg) {

    }

    @FXML
    public void onHostButtonClick(ActionEvent event) throws IOException {
//        sc.setScene(event, "host-view.fxml");
//        HostVM hvm = vm;
//        hvm.startServer();
    }

    @FXML
    public void onLobbyButtonClick() throws IOException {
        HostVM hvm = (HostVM) View.vm;
        hvm.startServer();
        Main.setScene("LobbyView");
    }

    public void onTestDMServerConnection(ActionEvent event) {
        HostVM hvm = (HostVM) View.vm;
        hvm.testDMServerConnection();

    }
}
