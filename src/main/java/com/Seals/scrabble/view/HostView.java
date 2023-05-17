package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.vm.HostVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HostView extends View {

    @FXML
    public void onHostButtonClick(ActionEvent event) throws IOException {
        // Handle host button click event
    }

    @FXML
    public void onLobbyButtonClick() throws IOException {
        if (View.vm instanceof HostVM) {
            HostVM hostVM = (HostVM) View.vm;
            hostVM.startServer();
        }
        Main.setScene("LobbyView");
    }

    public void onTestDMServerConnection(ActionEvent event) {
        if (View.vm instanceof HostVM) {
            HostVM hostVM = (HostVM) View.vm;
            hostVM.testDMServerConnection();
        }
    }

    public HostView() {
        super();
    }
}
