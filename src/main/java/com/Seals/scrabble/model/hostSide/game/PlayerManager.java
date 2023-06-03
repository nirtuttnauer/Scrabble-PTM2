package com.Seals.scrabble.model.hostSide.game;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public PlayerManager() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
        System.out.println(this.players);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public Player getPlayer(int playerId) {
        for (Player player : players) {
            if (player.getId() == playerId) {
                return player;
            }
        }
        return null;
    }

    public int getTotalPlayers() {
        return players.size();
    }

    public void sendMessageToPlayer(int playerId, String message) {
        Player player = getPlayer(playerId);
        if (player != null) {
            player.sendToPlayer(playerId, message);
        }
    }

    public Player addPlayer(PrintWriter outputStream) {
        Player player = Player.createPlayer(outputStream);
        if (player != null) {
            addPlayer(player); // Add player to player manager
            System.out.println("Total players: " + getTotalPlayers());
            return player;
        } else {
//            System.out.println("Player limit reached");
            return null;
        }
    }

    public void initializePlayerHands() {
        for (Player player : players) {
            for (int i = 0; i < Player.MAX_TILES; i++) {
                player.addTile(Tile.Bag.getBag().getRand());
            }
        }
    }

    public void notifyCurrentPlayerTurn() {
//        Player currentPlayer = getCurrentPlayer(); // or however you retrieve the current player
//        currentPlayer.sendCommand("your_turn");
        System.out.println("TM");
    }
}
