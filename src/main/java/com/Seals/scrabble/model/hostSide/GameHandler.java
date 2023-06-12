package com.Seals.scrabble.model.hostSide;

import com.Seals.scrabble.factories.command.CommandFactory;
import com.Seals.scrabble.factories.command.ICommand;
import com.Seals.scrabble.model.socketUtil.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class GameHandler implements ClientHandler {

    private CommandFactory commandFactory;
    BufferedReader in;
    PrintWriter out;
    private Socket clientSocket;

    public GameHandler(Socket socket) {
        this.commandFactory = new CommandFactory();
        this.clientSocket = socket;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public void handleClient(InputStream inFromClient, OutputStream outToClient) throws IOException {
        if (in == null) in = new BufferedReader(new InputStreamReader(inFromClient, StandardCharsets.UTF_8));
        if (out == null) this.out = new PrintWriter(new OutputStreamWriter(outToClient, StandardCharsets.UTF_8), true);

        String request;
        while ((request = in.readLine()) != null) {
            System.out.println("Received request: " + request);
            String response = processRequest(request);
            try {
                sendMessage(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() {
        try {
            if (out != null) {
                out.flush();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void sendMessage(String message) throws IOException {
        if (out != null) {
            out.println(message);
            out.flush();
        } else {
            System.out.println("out is null : line 69 : gameHandler");
        }
    }

    private String processRequest(String request) {
        System.out.println("processRequerst::GameHandler " + request + " on line 82");
        if (request == null || request.isEmpty()) {
            return "Invalid request";
        }

        List<String> split = new ArrayList<>(List.of(request.split(",")));
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
