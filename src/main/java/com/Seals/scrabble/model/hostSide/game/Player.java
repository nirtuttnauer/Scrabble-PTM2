package com.Seals.scrabble.model.hostSide.game;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class Player {
    public static final int MAX_TILES = 7;
    private static int nextId = 1;
    private static final int MAX_PLAYERS = 4;
    private int id;
    private List<Tile> hand;
    private Player() {
        this.id = nextId++;
        this.hand = new ArrayList<>();
    }

    public static Player createPlayer() {
        if (nextId <= MAX_PLAYERS) {
            return new Player();
        } else {
            System.out.println("Player limit reached");
            return null;
        }
    }

    public List<Tile> getHand() {
        return hand;
    }

    public void addTile(Tile tile) {
        while (hand.size() != MAX_TILES) {
            Tile t = Tile.Bag.getBag().getRand();
//            if (t != null){
            hand.add(t);
//            }
        }
    }

    public List<Tile> addTilesFromString(String w) {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < getHand().size(); i++) {
            try {
            tiles.add(Tile.Bag.getBag().getTile(w.charAt(i)));
            }
            catch (Exception e){
                System.out.println("Cannot get tile (error)");
            }
        }
        return tiles;
    }

    public void removeTilesFromHand(Tile[] tiles) {
        for (Tile tile : tiles) {
            hand.remove(tile);
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Player " + getId();
    }

    private Player getPlayer(int playerId) {
        return getGameManager().getPlayer(playerId);
    }

    public void printHand() {
        List<Tile> tiles = this.getHand();
        String hand = tiles.stream().map(tile -> String.valueOf(tile.getLetter())).collect(Collectors.joining(", "));
        System.out.println("Your hand: " + hand);
    }

}
