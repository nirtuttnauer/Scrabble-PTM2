package com.Seals.scrabble.factories;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;

public class SceneFactory {
    static HashMap<String, Scene> map = new HashMap<>();

    public SceneFactory() throws IOException {
        map.put("WelcomeView", createScene("welcome-view.fxml"));
        map.put("GameView", createScene("game-view.fxml"));
        map.put("LeaderboardView",createScene("welcome-view.fxml"));
        map.put("SettingsView", createScene("settings-view.fxml"));
        map.put("MenuView", createScene("menu-view.fxml"));
        map.put("JoinView", createScene("join-view.fxml"));
        map.put("HostView", createScene("host-view.fxml"));
        map.put("LobbyView", createScene("lobby-view.fxml"));

    }

    public static Scene createScene(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
        Scene scene = new Scene(loader.load(), Settings.getHEIGHT(), Settings.getWIDTH());
        return scene;
    }
    public HashMap<String, Scene> getSceneMap() {
        return map;
    }
}
