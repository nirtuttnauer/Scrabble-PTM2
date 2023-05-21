package com.Seals.scrabble;

import com.Seals.scrabble.factories.SceneFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public class Main extends Application {
    private static Stage window;
    private static final SceneFactory sceneFactory = new SceneFactory();

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
        window = primaryStage;
        setScene("LoginView");
    }
}
