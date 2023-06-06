package com.Seals.scrabble.model.hostSide.game;

import java.util.ArrayList;
import java.util.List;


import static com.Seals.scrabble.model.hostSide.game.Player.MAX_TILES;

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


    public Player addPlayer() {
        Player player = Player.createPlayer();
        if (player != null) {
            addPlayer(player);
            System.out.println("Total players: " + getTotalPlayers());
            return player;
        } else {
            return null;
        }
    }

    public void initializePlayerHands() {
        for (Player player : players) {
                player.addTile(Tile.Bag.getBag().getRand());
        }
    }


    public void notifyCurrentPlayerTurn() {
        System.out.println("TU");
    }

    public void removePlayerById(int playerId) {
        removePlayer(getPlayer(playerId));
    }
}
