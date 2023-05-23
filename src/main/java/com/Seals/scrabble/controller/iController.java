package com.Seals.scrabble.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.IOException;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public interface iController {
    default void onMenuButtonClick() throws IOException{
        setScene("menu-view.fxml");
    };

    default void onExitButtonClick() throws IOException
    {
        Platform.exit();
    };


}
