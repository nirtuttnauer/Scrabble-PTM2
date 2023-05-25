package com.Seals.scrabble.model.hostSide;

import com.Seals.scrabble.model.hModel;
import com.Seals.scrabble.model.hostSide.game.GameManager;
import com.Seals.scrabble.model.hostSide.game.Player;
import com.Seals.scrabble.model.socketUtil.ClientHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameHandler implements ClientHandler {

    private GameManager gm;
    private Scanner scanner;
    private PrintWriter writer;


    public GameHandler(hModel hostModel) {
        gm = hModel.getGameManager();
    }

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        Scanner scanner = new Scanner(inFromClient);
        PrintWriter writer = new PrintWriter(outToClient, true); // Auto-flush enabled

//        while (scanner.hasNextLine())
        String request = scanner.nextLine();

        // Process the request and generate a response
        String response = processRequest(request);

        // Send the response back to the client
        writer.println(response != null ? response : "Request received");
    }


    @Override
    public void close() {
        // Cleanup or additional operations when the handler is closed
    }

    private String processRequest(String request) {
        String[] split = request.split(",");
        System.out.println(request);
        // Create a new player for this connection
        if (split[0].equals("PL")) {
            gm.performAction("PL", Integer.parseInt(split[1]), split[2]);
            gm.getGameBoard().printBoard();
        }
        if (split[0].equals("PA")) {
            gm.performAction("PA", Integer.parseInt(split[1]), null);
            gm.getGameBoard().printBoard();
        }
        if (split[0].equals("EX")) {
            gm.performAction("EX", Integer.parseInt(split[1]), null);
            gm.getGameBoard().printBoard();
        }
        if (split.equals("N") && gm.getTotalPlayers() < 4) {
            request = String.valueOf(gm.addPlayer());
        } else System.out.println("Player limit reached");
        // Implement your logic to process the request and generate a response
        // Example: Echo the request as the response
        return request;
    }
}
