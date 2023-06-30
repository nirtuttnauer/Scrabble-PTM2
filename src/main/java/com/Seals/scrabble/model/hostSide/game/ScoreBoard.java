package com.Seals.scrabble.model.hostSide.game;

import java.util.HashMap;
import java.util.Map;

import static com.Seals.scrabble.model.hModel.getGameManager;

class ScoreBoard {
    private Map<Player, Integer> scores;

    public ScoreBoard() {
        this.scores = new HashMap<>();
    }

    public void updateScore(Player player, int score) {
        scores.put(player, scores.getOrDefault(player, 0) + score);
        System.out.println(player + " now has " + scores.get(player) + " points");
    }

    public void displayScoreboard() {
        StringBuilder sb = new StringBuilder();
        for (Integer v : scores.values()) {
            sb.append(',').append(String.valueOf(v));
        }
        getGameManager().getGameServer().broadcast("scores"+sb.toString());

    }

    public void addPlayer(Player player) {
        scores.put(player, 0); // Add player to scoreboard with 0 points
    }
}