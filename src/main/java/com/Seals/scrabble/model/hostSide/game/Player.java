package com.Seals.scrabble.model.hostSide.game;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class Player {
    private static int nextId = 1;
    private static final int MAX_PLAYERS = 4;
    private static final int MAX_TILES = 7;
    private int id;
    private List<Tile> tiles;
    private PrintWriter outputStream; // The stream for sending messages to this player

    private Player(PrintWriter outputStream) {
        this.id = nextId++;
        this.tiles = new ArrayList<>();
        this.outputStream = outputStream;
    }

    public static Player createPlayer(PrintWriter outputStream) {
        if (nextId <= MAX_PLAYERS) {
            return new Player(outputStream);
        } else {
            System.out.println("Player limit reached");
            return null;
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public boolean addTile(Tile tile) {
        if (tiles.size() < MAX_TILES) {
            tiles.add(tile);
            return true;
        }
        return false;
    }

    public boolean removeTile(Tile tile) {
        return tiles.remove(tile);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Player " + getId();
    }

    public void sendMessage(String message) {
        outputStream.println(message);
    }

    // It's a good idea to provide a way to close the output stream when we're done
    public void closeStream() {
        outputStream.close();
    }

    public void sendToPlayer(int playerId, String message) {
        Player player = this.getPlayer(playerId);
        if (player != null) {
            player.sendMessage(message);
        }
    }

    private Player getPlayer(int playerId) {
        return getGameManager().getPlayer(playerId);
    }

    public void printHand() {
        List<Tile> tiles = this.getTiles();
        String hand = tiles.stream()
                .filter(Objects::nonNull)
                .map(tile -> String.valueOf(tile.getLetter()))
                .collect(Collectors.joining(", "));
        System.out.println("Your hand: " + hand);
    }

}
