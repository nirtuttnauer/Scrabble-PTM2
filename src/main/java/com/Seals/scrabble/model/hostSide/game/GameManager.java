package com.Seals.scrabble.model.hostSide.game;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private Board gameBoard;
    private Tile.Bag tileBag;
    private List<Player> players;

    public GameManager() {
        this.gameBoard = Board.getBoard();
        this.tileBag = Tile.Bag.getBag();
        this.players = new ArrayList<>();
    }

    public void startGame() {
        // Perform any necessary initialization for starting the game
        System.out.println("Scrabble game started");
        gameBoard.printBoard();
    }

    public void endGame() {
        // Perform any necessary cleanup for ending the game
        System.out.println("Scrabble game ended");
    }

    public void performAction(String action, int playerId) {
        // Process game actions based on the provided action parameter
        switch (action) {
            case "pass":
                System.out.println("Player " + playerId + " passed their turn");
                break;
            case "exchange":
                System.out.println("Player " + playerId + " wants to exchange tiles");
                // Exchange tiles
                Player player = players.get(playerId - 1);
                List<Tile> playerTiles = player.getTiles();
                List<Tile> exchangedTiles = new ArrayList<>();
                for (Tile tile : playerTiles) {
                    exchangedTiles.add(tileBag.getTile(tile.getLetter()));
                    tileBag.put(tile);
                }
                player.setTiles(exchangedTiles);
                break;
            // Add more game actions as needed
            default:
                System.out.println("Unknown game action: " + action);
        }
    }

    public Board getGameBoard() {
        return this.gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean placeWord(Word word) {
        if (gameBoard.boardLegal(word) && gameBoard.dictionaryLegal(word)) {
            int score = gameBoard.tryPlaceWord(word);
            System.out.println("Word placed successfully. Score: " + score);
            gameBoard.printBoard(); // Print the updated board
            return true;
        } else {
            System.out.println("Invalid word placement");
            return false;
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayer(int playerId) {
        if (playerId >= 1 && playerId <= players.size()) {
            return players.get(playerId - 1);
        } else {
            return null;
        }
    }
}
