package com.Seals.scrabble.factories;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.Settings;
import com.Seals.scrabble.controller.LoginController;
import com.Seals.scrabble.controller.MenuController;
import com.Seals.scrabble.controller.iController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;

import static com.Seals.scrabble.Main.getWindow;

public class SceneFactory {
    private static HashMap<String, String> viewPaths = new HashMap<>();
    private static HashMap<String, iController> controllers = new HashMap<>();

    public SceneFactory() {
        viewPaths.put("LoginView", "login-view.fxml");
        viewPaths.put("MenuView", "menu-view.fxml");
        viewPaths.put("GameView", "game-view.fxml");
        viewPaths.put("LeaderboardView", "leaderboard-view.fxml");
        viewPaths.put("SettingsView", "settings-view.fxml");
        viewPaths.put("LobbyView", "lobby-view.fxml");
        viewPaths.put("HostView", "host-view.fxml");
        viewPaths.put("JoinView", "join-view.fxml");
    }

    public static void setScene(String viewName) {
        if (viewPaths.get(viewName) == null) {
            System.out.println("view not found");
            return;
        }
        String viewPath = viewPaths.get(viewName);
        if (viewPath != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource(viewPath));
                Scene scene = new Scene(loader.load(), Settings.getWIDTH(), Settings.getHEIGHT());
                scene.getStylesheets().add(SceneFactory.class.getResource(Settings.getCssPath()).toExternalForm());
                iController controller = loader.getController();

                // Store the controller for later use
                controllers.put(viewName, controller);

                // Assuming you have a ViewModel instance called viewModel

                if (controller instanceof LoginController) {
                    LoginController loginController = (LoginController) controller;
                    loginController.setViewModel(ViewModelFactory.getVM());
                } else if (controller instanceof MenuController) {
                    MenuController menuController = (MenuController) controller;
                    menuController.setViewModel(ViewModelFactory.getVM());
                }


                // Set the scene and show the window
                getWindow().setScene(scene);
                getWindow().show();


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("failed to load view");
        }
    }

    public static HashMap<String, iController> getControllers() {
        return controllers;
    }

    public <T> T getController(String viewName) {
        return (T) controllers.get(viewName);
    }




}