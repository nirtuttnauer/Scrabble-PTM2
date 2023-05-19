package com.Seals.scrabble.model;

import com.Seals.scrabble.model.hostSide.GameHandler;
import com.Seals.scrabble.model.hostSide.manager.GameManager;
import com.Seals.scrabble.model.socketUtil.MyServer;
import com.Seals.scrabble.model.socketUtil.SocketUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class HostModel extends Model {
    private MyServer gameServer;
    private GameManager gameManager;

    public HostModel() {
        Random r = new Random();
        int port = 6000 + r.nextInt(1000);
        gameServer = new MyServer(port, new GameHandler());
        gameManager = new GameManager();
    }

    public void startServer() {
        gameServer.start();
        System.out.println("Server started on port " + gameServer.getPort());
    }

    public void stopServer() {
        gameServer.close();
        System.out.println("Server closed on port " + gameServer.getPort());
    }

//    public void handleClientRequest(Socket client) throws IOException {
//        try {
//            gameServer.getCh().handleClient(client.getInputStream(), client.getOutputStream());
//        } catch (IOException e) {
//            System.out.println("Error handling client request: " + e.getMessage());
//        } finally {
//            SocketUtil.finallyClose(client, client.getOutputStream(),client.getInputStream());
//        }
//    }

    public String sendRequestToServer(String query) {
        Socket client = null;
        PrintWriter out = null;
        Scanner in = null;
        String response = "";

        try {
            client = new Socket("localhost", gameServer.getPort());
            out = new PrintWriter(client.getOutputStream());
            in = new Scanner(client.getInputStream());

            out.println(query);
            out.flush();
            response = in.next();
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

    public void performGameAction(String action) {
        gameManager.performAction(action);
        System.out.println("Performed game action: " + action);
    }

    public MyServer getGameServer() {
        return gameServer;
    }
}
