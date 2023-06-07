package com.Seals.scrabble.model;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.model.socketUtil.SocketUtil;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;

public class Model extends Observable implements iModel {
    private String serverAddress = "localhost";
    private int hostPort;
    private int ID;
    private Socket guestSocket;
    private PrintWriter out = null;
    private Scanner in = null;

    //IO listener
    private Thread listenerThread;
    private boolean listening;
    //model data
    private StringProperty currentTurn;
    private StringProperty nickname;


    public Model() {
        this.nickname = new SimpleStringProperty();
        this.currentTurn = new SimpleStringProperty();
        hostPort = Settings.getHostServerPort();
        this.listening = false;
    }

    public Model(iModel model) {
        if (model == null) {
            throw new IllegalArgumentException("model cannot be null");
        }
        this.nickname = model.nicknameProperty();
        this.serverAddress = model.getServerAddress();
        this.hostPort = model.getHostPort();
        this.guestSocket = model.getGuestSocket();
        this.listening = false;
        this.out = model.getOut();
        this.in = model.getIn();
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public Socket getGuestSocket() {
        return guestSocket;
    }

    public void setGuestSocket(Socket guestSocket) {
        this.guestSocket = guestSocket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public Scanner getIn() {
        return in;
    }

    public void setIn(Scanner in) {
        this.in = in;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNickname(String name) {
        nickname.set(name);
    }

    public String getNickname() {
        return nickname.get();
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }

    public void connectToHost() {
        try {
            System.out.println("Connecting to host on port " + hostPort);
            guestSocket = new Socket(serverAddress, Settings.getHostServerPort());
            guestSocket.setSoTimeout(1000); // Set the timeout to 5 seconds (5000 milliseconds)
            out = new PrintWriter(guestSocket.getOutputStream(), true); // Sending request to server
            in = new Scanner(guestSocket.getInputStream());
            System.out.println("Just connected to " + guestSocket.getRemoteSocketAddress());
            startListening();
            sendRequestToHost("NP", new String[]{getNickname()}); // Request for ID of new player
        } catch (IOException e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }

    private void startListening() {
        listening = true;
        System.out.println("started listing on line 130");
        listenerThread = new Thread(() -> {
            while (listening) {
                if (in.hasNextLine()) {
                    System.out.println("I heard something...");
                    String response = in.nextLine();
                    System.out.println(response);
                    processResponse(response);
                }
            }
        });
        listenerThread.start();
    }

    private void stopListening() {
        listening = false;
        if (listenerThread != null) {
            listenerThread.interrupt();
        }
    }

    private void processResponse(String response) {
        String[] parts = response.split(":");
        if (parts[0].equals("TU")) {
            int turnId = Integer.parseInt(parts[1]);
            if (turnId == this.ID) {
                Platform.runLater(this::onMyTurn);
            }
        }
    }

    private void onMyTurn() {
        setChanged();
        notifyObservers("MT");

        // Send a response to the server when it's this player's turn
        sendRequestToHost("MTR", null);
    }

    public void disconnectFromHost() {
        stopListening();
        sendRequestToHost("QU", new String[]{String.valueOf(this.ID)});
        SocketUtil.finallyClose(guestSocket, out, in);
        System.out.println("Disconnected from host");
    }


    public void sendRequestToHost(String commandName, String[] args) {
        try {
            if (guestSocket.isClosed()) {
                System.out.println("Socket is closed");
                guestSocket = new Socket(serverAddress, Settings.getHostServerPort());
                guestSocket.setSoTimeout(1000); // Set the timeout to 5 seconds (5000 milliseconds)
                out = new PrintWriter(guestSocket.getOutputStream(), true); // Sending request to server
            }

            // Format the command and arguments into a request string
            String request = commandName;
            if (args != null) {
                request += ":" + String.join(",", args);
            }

            out.println(request);
            // Handle response
            // Don't close socket, scanner, or writer here;
            // they should be closed when done with all communication
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//    private String processResponse(String response) {
//        if (response == null || response.isEmpty()) {
//            return "Invalid request";
//        }
//
//        String[] split = response.split(",");
//        ICommand command = getCommandFactory().getCommand(split[0]);
//        if (command != null) {
//            return command.execute(split);
//        } else {
//            return "Unknown command: " + split[0];
//        }
//    }


}
