package com.Seals.scrabble.model.hostSide;

import com.Seals.scrabble.factories.command.CommandFactory;
import com.Seals.scrabble.factories.command.ICommand;
import com.Seals.scrabble.model.hModel;
import com.Seals.scrabble.model.hostSide.game.GameManager;
import com.Seals.scrabble.model.socketUtil.ClientHandler;

import java.io.*;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class GameHandler implements ClientHandler {
    private final GameManager gameManager;
    private static CommandFactory commandFactory = null;
    private BufferedWriter clientWriter;

    public GameHandler() {
        this.gameManager = getGameManager();
        commandFactory = new CommandFactory();
    }

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        try {
            clientWriter = new BufferedWriter(new PrintWriter(outToClient, true));
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(inFromClient));

            String request;
            while ((request = clientReader.readLine()) != null) {
                System.out.println("Received request: " + request);
                String response = processRequest(request);
                if (response != null) {
                    clientWriter.write(response);
                    clientWriter.newLine();
                    clientWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public void close() {
        try {
            if (clientWriter != null) {
                clientWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processRequest(String request) {
        if (request == null || request.isEmpty()) {
            return "Invalid request";
        }

        String[] split = request.split(",");
        ICommand command = commandFactory.getCommand(split[0]);
        if (command != null) {
            return command.execute(split);
        } else {
            return "Unknown command: " + split[0];
        }
    }

    public static CommandFactory getCommandFactory() {
        return commandFactory;
    }
}
