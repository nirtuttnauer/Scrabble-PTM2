package com.Seals.scrabble.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.IOException;

public interface iController {
    void onMenuButtonClick() throws IOException;

     void onExitButtonClick() throws IOException;

}