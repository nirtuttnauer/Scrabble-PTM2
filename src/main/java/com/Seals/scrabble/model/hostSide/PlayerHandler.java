package com.Seals.scrabble.model.hostSide;

import com.Seals.scrabble.factories.command.CommandFactory;
import com.Seals.scrabble.factories.command.ICommand;
import com.Seals.scrabble.model.socketUtil.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class PlayerHandler implements ClientHandler {

    private final CommandFactory commandFactory;
    private PrintWriter clientWriter;
    private Socket clientSocket;

    public PlayerHandler() {
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

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void setClientSocket(Socket client) {

    }

private String processRequest(String request) {
    if (request == null || request.isEmpty()) {
        return "Invalid request";
    }

    List<String> split = new java.util.ArrayList<>(List.of(request.split(",")));
    System.out.println(split);
    String scmd = split.get(0);
    ICommand command = commandFactory.getCommand(scmd);
    split.remove(split.get(0));
    if (command != null) {
        String[] args = new String[split.size()];
        split.toArray(args);
        return command.execute(this.clientSocket, args);
    } else {
        return "Unknown command: " + scmd;
    }
}

    public PrintWriter getOut() {
        return clientWriter;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }
}
