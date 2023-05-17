package com.Seals.scrabble;


import com.Seals.scrabble.factories.SceneFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage window;
    static SceneFactory sceneFactory;




    public static Stage getWindow() {
        return window;
    }

    static void setWindow(Stage window) throws IOException {
        Main.window = window;
        sceneFactory = new SceneFactory();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        setWindow(primaryStage);
        setScene("WelcomeView");
    }

    public static void setScene(String path) throws IOException {
        getWindow().setScene(sceneFactory.getSceneMap().get(path));
        getWindow().show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
