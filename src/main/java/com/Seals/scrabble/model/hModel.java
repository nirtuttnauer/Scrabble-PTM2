package com.Seals.scrabble.model;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.model.hostSide.GameHandler;
import com.Seals.scrabble.model.hostSide.game.GameManager;
import com.Seals.scrabble.model.socketUtil.SocketUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class hModel extends Model {
    static private GameManager single_instance_gm = null;
    private int currentPlayer;

    public hModel(iModel model) {
        super(model);

        System.out.println("HostModel constructor called");
//        Random r = new Random();
//        int port = 6000 + r.nextInt(1000);
        getGameManager();

    }

    public void startServer() {
        getGameManager().startServer();
        connectToHost();
        sendRequestToHost("NP", null);
    }



    public String sendRequestToServer(String query) {
        Socket client = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String response = "";

        try {
            client = new Socket("localhost", Settings.getDMServerPort());
            out = new PrintWriter(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out.println(query);
            out.flush();
            response = in.readLine();
        } catch (IOException e) {
            System.out.println("Your code ran into an IOException: " + e.getMessage());
        } finally {
            try {
                SocketUtil.finallyClose(client, out, in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return String.valueOf(response.equals("true"));
    }

    public void startGame() {
        getGameManager().startGame();
        System.out.println("Game started");
    }

    public void endGame() {
        getGameManager().endGame();
        System.out.println("Game ended");
    }


//    public void testDMServerConnection() {
//        System.out.println("Testing DM server connection");
//        sendRequestToServer("q,mobidick," + getNickname());
//    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public String getNickname() {
        // Implement the getNickname method
        return null;
    }

    public int getTotalPlayers() {
        return single_instance_gm.getTotalPlayers();
    }

    public static GameManager getGameManager() {
        if (single_instance_gm == null) single_instance_gm = new GameManager();
        return single_instance_gm;
    }
}
