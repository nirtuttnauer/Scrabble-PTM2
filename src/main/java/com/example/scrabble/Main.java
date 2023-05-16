package com.example.scrabble;


import com.example.scrabble.factories.SceneFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage;
    static SceneFactory sceneFactory;




    public static Stage getStage() {
        return stage;
    }

    private static void setStage(Stage stage) throws IOException {
        Main.stage = stage;
        sceneFactory = new SceneFactory();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        setStage(primaryStage);
        setScene("WelcomeView");
    }

    public static void setScene(String path) throws IOException {
        getStage().setScene(sceneFactory.getSceneMap().get(path));
        getStage().show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
