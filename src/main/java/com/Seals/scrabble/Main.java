package com.Seals.scrabble;

import com.Seals.scrabble.factories.SceneFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage window;
    private static SceneFactory sceneFactory;

    public static SceneFactory getSceneFactory() {
        return sceneFactory;
    }

    public static Stage getWindow() {
        return window;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Platform.runLater(() -> {
            window = primaryStage;
            sceneFactory = new SceneFactory();
            sceneFactory.setScene("LoginView");
        });
    }
}
