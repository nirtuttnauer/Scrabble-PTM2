package com.Seals.scrabble.factories;

import com.Seals.scrabble.vm.IVM;

import java.util.HashMap;

public class VMFactory {
    static HashMap<String, IVM> map = new HashMap<>();

    public VMFactory() {
//        map.put("WelcomeView", new vmWelcomeView());
//        map.put("GameView", new vmGameView());
//        map.put("LeaderboardView", new vmLeaderboardView());
//        map.put("SettingsView", new vmSettingsView());
//        map.put("MenuView", new vmMenuView());
//        map.put("HostView", new vmHostView());
//        map.put("JoinView", new vmJoinView());
    }

    public HashMap<String, IVM> getVMMap() {
        return map;
    }
}