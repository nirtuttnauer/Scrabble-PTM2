package com.Seals.scrabble.model.hostSide.game;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.Seals.scrabble.model.hostSide.game.Player.createPlayer;

public class GameManager {
    private Board gameBoard;
    private Tile.Bag tileBag;
    private PlayerManager playerManager;
    private TurnManager turnManager;
    private ScoreBoard scoreBoard;

    public GameManager() {
        this.gameBoard = Board.getBoard();
        this.tileBag = Tile.Bag.getBag();
        this.playerManager = new PlayerManager();
    }

    public void updateScore(Player player, int score) {
        scoreBoard.updateScore(player, score);
    }

    public void displayScoreboard() {
        scoreBoard.displayScoreboard();
    }

    public int getTotalPlayers() {
        return playerManager.getTotalPlayers();
    }


    public Player getPlayer(int playerId) {
        return playerManager.getPlayer(playerId);
    }///


    public Player getCurrentPlayer() {
        return turnManager.getCurrentPlayer();
    }

    public void nextTurn() {
        turnManager.nextTurn();
        displayScoreboard(); // Display the scoreboard after each turn
    }


    public Player addPlayer(PrintWriter outputStream) {
        Player player = createPlayer(outputStream);
        if (player != null) {
            for (int i = 0; i < 7; i++) { // give player 7 tiles
                player.addTile(tileBag.getRand());
            }
            playerManager.addPlayer(player); // Add player to player manager
            System.out.println("Total players: " + getTotalPlayers() + "(addPlayer)");
            // print the player's hand
            player.printHand();
            return player;
        } else {
//            System.out.println("Player limit reached");
            return null;
        }
    }


    public void sendMessageToPlayer(int playerId, String message) {
        Player player = getPlayer(playerId);
        if (player != null) {
            player.sendToPlayer(playerId, message);
        }
    }


    public void startGame() {
        System.out.println("Scrabble game started");
        this.turnManager = new TurnManager(playerManager.getPlayers()); // Initialize the turn manager
        this.scoreBoard = new ScoreBoard(); // Initialize the scoreboard
        gameBoard.printBoard();
    }

    public void endGame() {
        System.out.println("Scrabble game ended");
    }

    public void performAction(String action, int playerId, String[] words) {
        Player player = playerManager.getPlayer(playerId);
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

    public Word createWordFromStrings(String[] words) {
        try {
            boolean isVertical = !"H".equalsIgnoreCase(words[0]);
            int x = Integer.parseInt(words[1]);
            int y = Integer.parseInt(words[2]);
            String wordString = words[3];

            Tile[] tiles = createTilesFromString(wordString);

            return new Word(tiles, x, y, isVertical);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing word placement coordinates: " + e.getMessage());
            return null;
        }
    }

    public Tile[] createTilesFromString(String wordString) {
        Tile[] tiles = new Tile[wordString.length()];
        for (int i = 0; i < wordString.length(); i++) {
//            tiles[i] = new Tile(wordString.charAt(i));
        }
        return tiles;
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

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public int tryPlaceWordAction(Player player, String[] words) {
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

}
