package com.Seals.scrabble.model.hostSide;

import com.Seals.scrabble.factories.command.CommandFactory;
import com.Seals.scrabble.factories.command.ICommand;
import com.Seals.scrabble.model.socketUtil.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class GameHandler implements ClientHandler {

    private CommandFactory commandFactory;
    private PrintWriter clientWriter;
    private Socket clientSocket;

    public GameHandler() {
        this.commandFactory = new CommandFactory();
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        BufferedReader clientReader = new BufferedReader(new InputStreamReader(inFromClient));
        this.clientWriter = new PrintWriter(outToClient, true);

        try {
            String request;
            while ((request = clientReader.readLine()) != null) {
                System.out.println("Received request: " + request);
                String response = processRequest(request);
                sendMessage(response); // Send the response back to the client
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (clientWriter != null) {
            clientWriter.close();
        }
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void sendMessage(String message) {
        if (clientWriter != null) {
            clientWriter.println(message);
        }
    }

    private String processRequest(String request) {
        System.out.println(request + " hellooooo");
        if (request == null || request.isEmpty()) {
            return "Invalid request";
        }

        String[] split = request.split(",");
        System.out.println(Arrays.toString(split));
        ICommand command = commandFactory.getCommand(split[0]);
        if (command != null) {
            return command.execute(Arrays.toString(split));
        } else {
            return "Unknown command: " + split[0];
        }
    }
}
