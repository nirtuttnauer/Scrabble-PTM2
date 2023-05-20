package com.Seals.scrabble.model.hostSide.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static int nextId = 1;
    private int id;
    private List<Tile> tiles;

    public Player() {
        this.id = nextId++;
        this.tiles = new ArrayList<>();
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public void removeTile(Tile tile) {
        tiles.remove(tile);
    }

    public int getId() {
        return id;
    }
}