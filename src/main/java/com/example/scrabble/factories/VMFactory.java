package com.example.scrabble.factories;

import com.example.scrabble.vm.generics.IVM;
import com.example.scrabble.vm.generics.VM;
import com.example.scrabble.vm.view_vm.*;
import javafx.scene.Scene;

import java.util.HashMap;

import static com.example.scrabble.factories.SceneFactory.createScene;

public class VMFactory {
    static HashMap<String, IVM> map = new HashMap<>();

    public VMFactory() {
        map.put("WelcomeView", new vmWelcomeView());
        map.put("GameView", new vmGameView());
        map.put("LeaderboardView", new vmLeaderboardView());
        map.put("SettingsView", new vmSettingsView());
        map.put("MenuView", new vmMenuView());
        map.put("HostView", new vmHostView());
        map.put("JoinView", new vmJoinView());
    }

    public HashMap<String, IVM> getVMMap() {
        return map;
    }
}
