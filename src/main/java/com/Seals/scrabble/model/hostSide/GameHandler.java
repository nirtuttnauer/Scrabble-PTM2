package com.Seals.scrabble.model.hostSide;

import com.Seals.scrabble.factories.command.CommandFactory;
import com.Seals.scrabble.factories.command.ICommand;
import com.Seals.scrabble.model.socketUtil.ClientHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameHandler implements ClientHandler {

    private CommandFactory commandFactory;
    Scanner in;
    PrintWriter out;
    private Socket clientSocket;

    public GameHandler(Socket socket) {
        this.commandFactory = new CommandFactory();
        this.clientSocket = socket;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        if (in == null) in = new Scanner(inFromClient);
        if (out == null) this.out = new PrintWriter(outToClient, true);

        String request;
        while (true) {
            if (in.hasNextLine()) {
                request = in.nextLine();
                System.out.println("Received request: " + request + " on line 37 in gamehandler");
                String response = processRequest(request);
                try {
                    System.out.println(clientSocket.isConnected());
                    sendMessage(response); // Send the response back to the client
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void close() {
        try {
            out.flush();
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void sendMessage(String message) throws IOException {
//        if (out == null)
//        System.out.println("made it here l66 gh");
        if (out != null) {
            System.out.println(clientSocket.isConnected());

            out.println(message);
            out.flush();
            out.close();
        } else System.out.println("out is null : line 69 : gameHandler");
//        clientSocket = new Socket(clientSocket.getInetAddress().getHostAddress(), clientSocket.getPort());
//        this.out = new PrintWriter(,true);
//        out.println(message);
//        out.close();
    }

    private String processRequest(String request) {
        System.out.println("processRequerst::GameHandler " + request + " on line 82");
        if (request == null || request.isEmpty()) {
            return "Invalid request";
        }

        List<String> split = new java.util.ArrayList<>(List.of(request.split(",")));
//        System.out.println(split);
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


}
