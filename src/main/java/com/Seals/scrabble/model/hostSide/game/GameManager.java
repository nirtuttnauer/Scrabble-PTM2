package com.Seals.scrabble.model.hostSide.game;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.model.hostSide.GameHandler;
import com.Seals.scrabble.model.socketUtil.MyServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.Seals.scrabble.model.socketUtil.SocketUtil.delay;

public class GameManager {
    private Board gameBoard;

    public Tile.Bag getBag() {
        return Bag;
    }

    private Tile.Bag Bag;
    private PlayerManager playerManager;
    private TurnManager turnManager;
    private ScoreBoard scoreBoard;
    private boolean isGameInProgress;
    private GameHandler gameHandler;
    private String[] Books = {"search_folder/The Matrix.txt", "search_folder/Harray Potter.txt"};
    //server
    private MyServer gameServer;

    private Socket DMSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public GameManager() {
        this.gameBoard = Board.getBoard();
        this.Bag = Tile.Bag.getBag();
        this.playerManager = new PlayerManager();
        this.isGameInProgress = false;
        int port = Settings.getHostServerPort();
        this.turnManager = new TurnManager(playerManager.getPlayers());
        gameServer = new MyServer(port, new GameHandler(null));
        try {
            DMSocket = new Socket(Settings.getServerAddress(), Settings.getDMServerPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public TurnManager getTurnManager() {
        return turnManager;
    }

    public void startGame() {
        System.out.println("----Scrabble game started----");
        this.turnManager = new TurnManager(playerManager.getPlayers());
        this.scoreBoard = new ScoreBoard();
        gameBoard.printBoard();

//        // Waiting for the game to start
//        Scanner in = new Scanner(System.in);
//        while (!isGameInProgress) {
//            String input = in.nextLine();
//            if (input.equals("s")) {
//                isGameInProgress = true;
//            }
//        }

        // Game has started
        delay(1000);
        isGameInProgress = true;
        delay(2000);
        playerManager.initializePlayerHands();
        gameServer.broadcast("board," + getGameBoard().printBoardLetters());


        nextTurn();
        while (isGameInProgress) {
            processTurn();
//            gameBoard.printBoard();
            displayScoreboard();

            if (isGameFinished()) {
                isGameInProgress = false;
            } else {
                nextTurn();
            }
        }
    }

    private void processTurn() {
//        System.out.println(+ (getTurnManager().getCurrentPlayerIndex() + 1));
        Player currentPlayer = getPlayerManager().getPlayer(getTurnManager().getCurrentPlayerIndex() + 1);
        if (currentPlayer != null) {

//            gameServer.broadcast(currentPlayer.printHand());
//            gameServer.broadcast(currentPlayer.)
            StringBuilder stringBuilder = new StringBuilder();
            for (Player p : getPlayerManager().getPlayers()) {
                stringBuilder.append(',').append(p.getName());
            }


            gameServer.broadcast("players" + stringBuilder.toString());
            gameServer.broadcast("UA," + currentPlayer.printHand() + "," + currentPlayer.getId() + "," + this.Bag.getQuantitiesString());
//            gameServer.broadcast("board," + getGameBoard().printBoardLetters());
            gameServer.broadcast("turn," + (getTurnManager().getCurrentPlayerIndex() + 1));
//            gameServer.broadcast(() => {);

            // rest of the code
        } else {
            // handle the case when the player is not found
        }

        delay(2000);
    }

    private boolean isGameFinished() {
        return (Arrays.stream(Tile.Bag.getBag().getAmounts()).reduce(0, (x, y) -> x + y) == 0);

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


    public Boolean addPlayer(Player player) {
        if (player != null) {
            Boolean status = playerManager.addPlayer(player); // Add player to player manager
            System.out.println("Total players: " + getTotalPlayers());
            return status;
        } else {
            return null;
        }
    }

    public void endGame() {
        System.out.println("----Scrabble game ended----");
    }

    public boolean tryPlaceWordAction(int playerId, String word, int i, int j, boolean isVertical) {
        if (playerId != getPlayer(playerId).getId()) {
            throw new IllegalArgumentException("Player ID doesn't match the current player's ID");
        }

        Tile[] tiles = new Tile[0];
        try {
            tiles = getPlayer(playerId).addTilesFromString(word);
        } catch (Exception e) {
            e.printStackTrace();  // prints exception details including stack trace
            System.out.println("Error occurred in addTilesFromString: " + e.getMessage()); // prints the error message
        }

//        if (Arrays.stream(tiles).toList().isEmpty()) {
//            throw new IllegalArgumentException("Player doesn't have the necessary tiles to form this word.");
//        }
//        System.out.println(""+tiles[1].getLetter() + tiles[2].getLetter());
        Word wordToPlace = new Word(tiles, i, j, isVertical);

        int score = gameBoard.tryPlaceWord(wordToPlace);


        getPlayer(playerId).removeTilesFromHand(tiles);
        getPlayer(playerId).addTile(Tile.Bag.getBag().getRand());
        updateScore(getPlayer(playerId), score);
        gameBoard.printBoard();

        return (score != 0);
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

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

// game handler setter and getter


    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public void startServer() {
        gameServer.start();
        System.out.println("Server started on port " + gameServer.getPort());
    }

    public void stopServer() {
        gameServer.close();
        System.out.println("Server closed on port " + gameServer.getPort());
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    public MyServer getGameServer() {
        return gameServer;
    }

    public synchronized boolean query(Word w) {
        StringBuilder sb = new StringBuilder();
        for (Tile t : w.getTiles()) {
            sb.append(t.getLetter());
        }
        return Boolean.getBoolean(sendRequestToDM("Q", getBooks()[0], getBooks()[1], "" + sb.toString()));
    }

    public String[] getBooks() {
        return Books;
    }

    public void setBooks(String[] books) {
        Books = books;
    }

    public String sendRequestToDM(String... args) {
        final int maxRetries = 3;
        int attemptCount = 0;

        while (attemptCount < maxRetries) {
            attemptCount++;

            try {
                out = new PrintWriter(DMSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(DMSocket.getInputStream()));
                if (DMSocket == null || DMSocket.isClosed()) {

                    System.out.println("Socket is closed or null. Attempting to reconnect...");
                    DMSocket = new Socket(Settings.getServerAddress(), Settings.getDMServerPort());

                }

                StringBuilder request = new StringBuilder();
                if (args != null) {
                    request.append(String.join(",", args));
                }

                if (out != null) {
                    out.println(request);
                    out.flush();
                } else {
                    System.out.println("Output stream is null. Could not send request.");
                    return null;
                }

                if (in != null) {
                    String response = in.readLine();
                    if (response != null && !response.isEmpty()) {
                        System.err.println("!!!DM SERVER RESPONSE: " + response);
                        return response;
                    } else {
                        System.out.println("Server responded with an empty message.");
                        return null;
                    }
                } else {
                    System.out.println("Input stream is null. Could not read response.");
                    return null;
                }
            } catch (SocketTimeoutException e) {
                System.out.println("The server did not respond in time. Attempting to send request again...");
                if (attemptCount >= maxRetries) {
                    System.out.println("Server not responding after " + maxRetries + " attempts. Please check your connection.");
                    return null;
                }
            } catch (IOException e) {
                System.out.println("An error occurred when trying to send the request: " + e.getMessage());
                return null;
            }
        }
        System.out.println("Maximum retry attempts reached. Please check your connection.");
        return null;
    }

}

