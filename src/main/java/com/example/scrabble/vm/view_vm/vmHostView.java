package com.example.scrabble.vm.view_vm;

import com.example.scrabble.App;
import com.example.scrabble.vm.generics.IVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class vmHostView extends vmView implements Observer, IVM {

    public vmHostView() {
        super();
    }

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
        App.setScene("lobby-view.fxml");
    }

}
