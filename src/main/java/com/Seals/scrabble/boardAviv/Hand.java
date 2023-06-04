package com.Seals.scrabble.boardAviv;

import com.Seals.scrabble.model.hostSide.game.Tile;

import java.util.Arrays;


public class Hand {
    String[] tiles;
    public Hand(String s) {
        tiles=s.split("");
        System.out.println("From Hand class :");
        printHand();
    }

    public String[] getTiles() {
        return tiles;
    }

    public void printHand(){
        Arrays.stream(tiles).iterator().forEachRemaining(System.out::print);
    }

}
