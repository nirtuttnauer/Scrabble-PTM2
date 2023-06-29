package com.Seals.scrabble.model.hostSide.game;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static com.Seals.scrabble.model.hModel.getGameManager;
import static com.Seals.scrabble.model.hostSide.game.Player.MAX_PLAYERS;

public class PlayerManager {
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public PlayerManager() {
        this.players = new ArrayList<>();
    }

    public Boolean addPlayer(Player player) {
        if (this.getTotalPlayers() < MAX_PLAYERS) {
            players.add(player);
            System.out.println(this.players + "line 24 PM");
            return true;
        }
        return false;
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


    public Player addPlayer(Socket socket, String nickname) {
        Player player = Player.createPlayer(socket, nickname);
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
//            getGameManager().getGameServer();
        }
    }


    public void notifyCurrentPlayerTurn() {
        getGameManager().getGameServer().broadcast("new update");
    }

    public void removePlayerById(int playerId) {
        removePlayer(getPlayer(playerId));
    }
}
