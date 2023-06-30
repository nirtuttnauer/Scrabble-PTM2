package com.Seals.scrabble.model;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.model.socketUtil.SocketUtil;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

import static com.Seals.scrabble.model.socketUtil.SocketUtil.delay;
import static java.lang.Math.abs;

public class Model extends Observable implements iModel {
    private String serverAddress = "localhost";
    private int hostPort;
    private int ID;
    private Socket guestSocket;
    private PrintWriter out = null;
    private BufferedReader in = null;

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
        getRandomName();
        addObserver(ViewModel.getSharedInstance());
    }

    public Model(iModel model) {
        if (model == null) {
            throw new IllegalArgumentException("model cannot be null");
        }
        addObserver(ViewModel.getSharedInstance());
        this.nickname = model.nicknameProperty();
        this.serverAddress = model.getServerAddress();
        this.hostPort = model.getHostPort();
        this.guestSocket = model.getGuestSocket();
        this.listening = false;
        this.out = model.getOut();
        this.in = model.getIn();
        addObserver(ViewModel.getSharedInstance());
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

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
        setChanged();
        notifyObservers("ID," + ID);
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
            guestSocket.setSoTimeout(5000); // Set the timeout to 5 seconds (5000 milliseconds)
            out = new PrintWriter(guestSocket.getOutputStream(), true); // Sending request to server
            in = new BufferedReader(new InputStreamReader(guestSocket.getInputStream())); // Change this line
            System.out.println("Just connected to " + guestSocket.getRemoteSocketAddress());
            startListening();
            sendRequestToHost("new player", getNickname()); // Request for ID of new player
        } catch (IOException e) {
            // Handle any exceptions
            connectToHost();
            e.printStackTrace();
        }
    }


    private void startListening() {
        listening = true;
//    System.out.println("started listing on line 130");
        listenerThread = new Thread(() -> {
            while (listening) {
                String response = null;
                try {
                    if ((response = in.readLine()) != null) {
//                    System.out.println("I heard something...");
                        System.out.println(response);
//                        processResponse(response);
                        sendRequestToHost(processResponse(response));
                    }
                } catch (SocketTimeoutException e) {
                System.out.println("Socket timeout, continuing to listen...");
                } catch (IOException e) {
                    e.printStackTrace();
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

    private String processResponse(String response) {
        System.out.println("Model: " + response);
        String[] parts = response.split(",");
        if (parts[0].equals("ID") || parts[0].equals("UA") || parts[0].equals("board") ||parts[0].equals("scores") || parts[0].equals("turn")|| parts[0].equals("players")) {
            setChanged();
            notifyObservers(response);
        }
//        System.out.println("update me");
        delay(3000);
        return "update me";
    }

    private void onMyTurn() {
        setChanged();
        notifyObservers("MT");

        // Send a response to the server when it's this player's turn
        sendRequestToHost("MTR");
    }

    public void disconnectFromHost() {
        stopListening();
        sendRequestToHost("QU", String.valueOf(this.ID));
        try {
            SocketUtil.finallyClose(guestSocket, out, in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Disconnected from host");
    }


    public void sendRequestToHost(String commandName, String... args) {
        try {
            if (guestSocket.isClosed()) {
                System.out.println("Socket is closed");
                guestSocket = new Socket(serverAddress, Settings.getHostServerPort());
//            guestSocket.setSoTimeout(5000); // Set the timeout to 5 seconds (5000 milliseconds)
                out = new PrintWriter(guestSocket.getOutputStream(), true); // Sending request to server
                in = new BufferedReader(new InputStreamReader(guestSocket.getInputStream())); // Ready to read response
            }

            // Format the command and arguments into a request string
            String request = commandName;
            if (args != null) {
                request += "," + String.join(",", args);
            }

            out.println(request);
            out.flush();

            // Read the response
            try {
                String response = in.readLine();
                if (response != null && !response.isEmpty()) {
                    String[] splitResponse = response.split(":");
                    if (splitResponse.length > 1 && splitResponse[0].equals("ID")) {
                        this.setID(Integer.parseInt(splitResponse[1]));
                    }
                }
            } catch (SocketTimeoutException e) {
                System.out.println("The server did not respond in time. Attempting to send request again...");
                sendRequestToHost(commandName, args);
            }

            // Don't close socket, scanner, or writer here;
            // they should be closed when done with all communication
        } catch (IOException e) {
            e.printStackTrace();
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

    void getRandomName() {
        Random r = new Random();
        String names = "yosi,inoy,aviv,omer,nir,hadar,maria,igor,efi,eli";
        String[] split = names.split(",");
        int rn = abs(r.nextInt() % split.length);
        this.nickname.set(split[rn]);
    }

}
