package com.Seals.scrabble.model;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.model.hostSide.GameHandler;
import com.Seals.scrabble.model.hostSide.game.GameManager;
import com.Seals.scrabble.model.hostSide.game.Player;
import com.Seals.scrabble.model.socketUtil.MyServer;
import com.Seals.scrabble.model.socketUtil.SocketUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class hModel extends Model {
    private MyServer gameServer;
    private GameManager gameManager;
    private Player currentPlayer;

    public hModel(iModel model) {
        super(model);
        System.out.println("HostModel constructor called");
        Random r = new Random();
        int port = 6000 + r.nextInt(1000);
        gameServer = new MyServer(port, new GameHandler());
        gameManager = new GameManager();
        currentPlayer = null;
    }

    public void startServer() {
        gameServer.start();
        System.out.println("Server started on port " + gameServer.getPort());
    }

    public void stopServer() {
        gameServer.close();
        System.out.println("Server closed on port " + gameServer.getPort());
    }

    public String sendRequestToServer(String query) {
        Socket client = null;
        PrintWriter out = null;
        Scanner in = null;
        String response = "";

        try {
            client = new Socket("localhost", Settings.getDefaultPort());
            out = new PrintWriter(client.getOutputStream());
            in = new Scanner(client.getInputStream());

            out.println(query);
            out.flush();
            if (in.hasNext()) response = in.nextLine();
        } catch (IOException e) {
            System.out.println("Your code ran into an IOException: " + e.getMessage());
        } finally {
            SocketUtil.finallyClose(client, out, in);
        }
        return response;
    }

    public void startGame() {
        gameManager.startGame();
        System.out.println("Game started");
    }

    public void endGame() {
        gameManager.endGame();
        System.out.println("Game ended");
    }

    public void performGameAction(String action, int playerId) {
        currentPlayer = gameManager.getPlayer(playerId);
        gameManager.performAction(action, currentPlayer.getId());

        System.out.println("Player " + playerId + " performed game action: " + action);
    }

    public MyServer getGameServer() {
        return gameServer;
    }

    public void testDMServerConnection() {
        System.out.println("Testing DM server connection");
      sendRequestToServer("q,mobidick," + getNickname());
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public String getNickname() {
        // Implement the getNickname method
        return null;
    }
}