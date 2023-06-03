package com.Seals.scrabble.model.hostSide.game;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.Seals.scrabble.model.hostSide.game.Player.createPlayer;
import static com.Seals.scrabble.model.socketUtil.SocketUtil.delay;

public class GameManager {
    private Board gameBoard;
    private Tile.Bag Bag;
    private PlayerManager playerManager;
    private TurnManager turnManager;
    private ScoreBoard scoreBoard;
    private boolean isGameInProgress;

    public GameManager() {
        this.gameBoard = Board.getBoard();
        this.Bag = Tile.Bag.getBag();
        this.playerManager = new PlayerManager();
        this.isGameInProgress = false;
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }

    public void startGame() {
        System.out.println("Scrabble game started");
        this.turnManager = new TurnManager(playerManager.getPlayers());
        this.scoreBoard = new ScoreBoard();
        gameBoard.printBoard();

        // Waiting for the game to start
        Scanner in = new Scanner(System.in);
        while (!isGameInProgress) {
            String input = in.nextLine();
            if (input.equals("s")) {
                isGameInProgress = true;
            }
        }

        // Game has started
        playerManager.initializePlayerHands();
        nextTurn();
        while (isGameInProgress) {
            processTurn();
            gameBoard.printBoard();
            displayScoreboard();

            if (isGameFinished()) {
                isGameInProgress = false;
            } else {
                nextTurn();
            }
        }
    }

    private void processTurn() {
        Player currentPlayer = getCurrentPlayer();
        currentPlayer.printHand();
        // wait for the response command (this will be game-specific, depending on your design)
        delay(5000);
    }

    private boolean isGameFinished() {
        return (Arrays.stream(Tile.Bag.getBag().getAmounts())
                .reduce(0, (x, y) -> x + y) == 0);

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
            playerManager.addPlayer(player); // Add player to player manager
            System.out.println("Total players: " + getTotalPlayers());
            return player;
        } else {
            return null;
        }
    }


    public void sendMessageToPlayer(int playerId, String message) {
        Player player = getPlayer(playerId);
        if (player != null) {
            player.sendToPlayer(playerId, message);
        }
    }


    public void endGame() {
        System.out.println("Scrabble game ended");
    }

    public boolean tryPlaceWordAction(Player player, String[] words) {
    if (words.length < 4) {
        throw new IllegalArgumentException("Insufficient arguments for placing a word");
    }
    boolean isVertical = !"H".equalsIgnoreCase(words[0]);
    int x = 0, y = 0;
    try {
        x = Integer.parseInt(words[1]);
        y = Integer.parseInt(words[2]);
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Error parsing word placement coordinates: " + e.getMessage());
    }
    String wordString = words[3];

    // create tiles from the wordString and get from player's hand
    List<Tile> tiles = player.addTilesFromString(wordString);
    if (tiles.isEmpty()) {
        throw new IllegalArgumentException("Player doesn't have the necessary tiles to form this word.");
    }
    Word wordToPlace = new Word(tiles.toArray(new Tile[0]), x, y, isVertical);

    // Place the word on the board
    boolean isSuccessful = placeWord(wordToPlace);
    if (isSuccessful) {
        player.removeTilesFromHand(tiles.toArray(new Tile[0])); // remove the tiles used from player's hand
        int score = gameBoard.tryPlaceWord(wordToPlace);
        updateScore(player, score);
        gameBoard.printBoard(); // Print the updated board
    }
    return isSuccessful;
}


    private void passTurnAction(Player player) {
        System.out.println("Player " + player.getId() + " passed their turn");
    }

    private void exchangeTilesAction(Player player, String[] lettersToExchange) {
        for (String letter : lettersToExchange) {
            Tile tile = null;
            if (tile != null) {
                player.removeTilesFromHand(new Tile[]{tile});
                player.addTile(Bag.getRand());
                Bag.put(tile);
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
            tiles[i] = Tile.Bag.getBag().getTile(wordString.charAt(i));
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


}
