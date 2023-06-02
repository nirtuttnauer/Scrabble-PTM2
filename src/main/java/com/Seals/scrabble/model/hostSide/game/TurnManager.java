package com.Seals.scrabble.model.hostSide.game;

import java.util.List;

public class TurnManager {
    private final List<Player> players;
    private int currentPlayerIndex;

    public TurnManager(List<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        if (players.isEmpty()) {
            return null;
        }
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() {
        if (!players.isEmpty()) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            System.out.println("It's now " + getCurrentPlayer() + "'s turn");
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        int indexToRemove = players.indexOf(player);
        if (indexToRemove != -1) {
            players.remove(indexToRemove);
            if (indexToRemove <= currentPlayerIndex) {
                currentPlayerIndex = (currentPlayerIndex == 0) ? players.size() - 1 : currentPlayerIndex - 1;
            }
        }
    }
}
