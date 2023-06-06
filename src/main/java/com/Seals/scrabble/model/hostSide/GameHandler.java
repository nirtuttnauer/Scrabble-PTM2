package com.Seals.scrabble.model.hostSide;

import com.Seals.scrabble.factories.command.CommandFactory;
import com.Seals.scrabble.factories.command.ICommand;
import com.Seals.scrabble.model.socketUtil.ClientHandler;

import java.io.*;

public class GameHandler implements ClientHandler {

    private CommandFactory commandFactory;
    private PrintWriter clientWriter;

    public GameHandler() {
        this.commandFactory = new CommandFactory();
    }

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        try {
            this.clientWriter = new PrintWriter(outToClient, true);
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(inFromClient));

            String request;
            while ((request = clientReader.readLine()) != null) {
                System.out.println("Received request: " + request);
                String response = processRequest(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public void close() {
        if (clientWriter != null) {
            clientWriter.close();
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

    public PrintWriter getOut() {
        return clientWriter;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }
}
