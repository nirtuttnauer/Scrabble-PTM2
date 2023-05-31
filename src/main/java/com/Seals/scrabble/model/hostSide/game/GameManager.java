package com.Seals.scrabble.model.hostSide.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.Seals.scrabble.model.hostSide.game.Player.createPlayer;

public class GameManager {
    private Board gameBoard;
    private Tile.Bag tileBag;
    private List<Player> players;
    private int currentPlayerIndex; // New attribute for turn management
    private Map<Player, Integer> scoreBoard; // New attribute for scoreboard

    public GameManager() {
        this.gameBoard = Board.getBoard();
        this.tileBag = Tile.Bag.getBag();
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0; // Initialize the current player index
        this.scoreBoard = new HashMap<>(); // Initialize the scoreboard
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

    public void performAction(String action, int playerId, String[] words) {
        Player player = getPlayer(playerId);
        if (player == null) {
            System.out.println("Player " + playerId + " not found");
            return;
        }

        int score = 0;
        switch (action) {
            case "PL":
                score = placeWordAction(player, words);
                break;
            case "PA":
                passTurnAction(player);
                break;
            case "EX":
                exchangeTilesAction(player, words);
                break;
            default:
                System.out.println("Unknown game action: " + action);
                return;
        }

        // Update score and advance to next turn
        updateScore(player, score);
        nextTurn();
    }

    private int placeWordAction(Player player, String[] words) {
        Word wordToPlace = createWordFromStrings(words);
        int score = 0;

        if (wordToPlace != null) {
            score = gameBoard.tryPlaceWord(wordToPlace);
            System.out.println("Player " + player.getId() + " placed a word. Score: " + score);
        } else {
            System.out.println("Word placement failed.");
        }
        return score;
    }

    private void passTurnAction(Player player) {
        System.out.println("Player " + player.getId() + " passed their turn");
    }

    private void exchangeTilesAction(Player player, String[] lettersToExchange) {
        for (String letter : lettersToExchange) {
            Tile tile = null;
            if (tile != null) {
                player.removeTile(tile);
                player.addTile(tileBag.getRand());
                tileBag.put(tile);
            }
        }
        System.out.println("Player " + player.getId() + " exchanged tiles");
    }

    private Word createWordFromStrings(String[] wordStrings) {
        // Implementation depends on the structure of Word and Tile classes.
        // This is a placeholder method to be replaced with the actual implementation.
        return null;
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

    public Player getPlayer(int playerId) {
        if (playerId >= 1 && playerId <= players.size()) {
            return players.get(playerId - 1);
        } else {
            return null;
        }
    }


    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }


    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        System.out.println("It's now " + getCurrentPlayer() + "'s turn");
    }

    public void updateScore(Player player, int score) {
        scoreBoard.put(player, scoreBoard.getOrDefault(player, 0) + score);
        System.out.println(getCurrentPlayer() + " now has " + scoreBoard.get(player) + " points");
    }

    public void displayScoreboard() {
        for (Player player : players) {
            System.out.println(player + ": " + scoreBoard.getOrDefault(player, 0) + " points");
        }
    }


    public int addPlayer() {
        Player p = createPlayer();
        if (p != null) {
            players.add(p);
            scoreBoard.put(p, 0); // Add player to scoreboard with 0 points
            System.out.println("total players: " + getTotalPlayers());
            return p.getId();
        }
        System.out.println("Player limit reached");
        return 0;
    }


    public int getTotalPlayers() {
        return players.size();
    }
}

