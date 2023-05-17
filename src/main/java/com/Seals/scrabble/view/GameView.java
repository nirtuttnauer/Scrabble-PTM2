package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.vm.generics.HostVM;
import javafx.fxml.FXML;

import java.io.IOException;

public class GameView extends View{


    @FXML
    public void onFinishButtonClick() throws IOException {
        Main.setScene("LeaderboardView");
        HostVM hvm = (HostVM) View.vm;
        hvm.closeServer();
    }
}
