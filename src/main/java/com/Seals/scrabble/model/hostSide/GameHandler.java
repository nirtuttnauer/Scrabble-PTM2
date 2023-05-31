package com.Seals.scrabble.model.hostSide;

import com.Seals.scrabble.factories.command.CommandFactory;
import com.Seals.scrabble.factories.command.ICommand;
import com.Seals.scrabble.model.hModel;
import com.Seals.scrabble.model.hostSide.game.GameManager;
import com.Seals.scrabble.model.socketUtil.ClientHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameHandler implements ClientHandler {
    private GameManager gm;
    private CommandFactory commandFactory;

    public GameHandler(hModel hostModel) {
        gm = hModel.getGameManager();
        commandFactory = new CommandFactory(gm);
    }

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        try (Scanner scanner = new Scanner(inFromClient);
             PrintWriter writer = new PrintWriter(outToClient, true)) {

            String request = scanner.nextLine();

            String response = processRequest(request);

            writer.println(response != null ? response : "Request received");
        }
    }

    @Override
    public void close() {

    }

    // Removed close() method since resources are now managed automatically.

    private String processRequest(String request) {
        if (request == null || request.isEmpty()) {
            System.err.println("Invalid request");
            return "Invalid request";
        }

        String[] split = request.split(",");
        System.out.println(request);

        ICommand command = commandFactory.getCommand(split[0]);
        if (command != null) {
            command.execute(split);
        } else {
            System.err.println("Unknown command: " + split[0]);
            return "Unknown command";
        }

        // Echo the request as the response
        return request;
    }
}
