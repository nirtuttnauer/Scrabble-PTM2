package com.Seals.scrabble.model;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.model.socketUtil.SocketUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;

public class Model extends Observable implements iModel {
    private StringProperty nickname;
    private String serverAddress = "localhost";
    private int hostPort;
    private int ID;
    private Socket guestSocket;
    private PrintWriter out = null;
    private Scanner in = null;

    public Model() {
        this.nickname = new SimpleStringProperty();
        hostPort = Settings.getHostServerPort();
    }

    public Model(iModel model) {
        if (model == null) {
            throw new IllegalArgumentException("model cannot be null");
        }
        this.nickname = model.nicknameProperty();
        this.serverAddress = model.getServerAddress();
        this.hostPort = model.getHostPort();
        this.guestSocket = model.getGuestSocket();
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
        } catch (IOException e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }


    public void disconnectFromHost() {
        SocketUtil.finallyClose(guestSocket, out, in);
        System.out.println("Disconnected from host");
    }


    public String sendRequestToHost(String request) {
        try {
            if (guestSocket.isClosed()) {
                System.out.println("Socket is closed");
                guestSocket = new Socket(serverAddress, Settings.getHostServerPort());
                guestSocket.setSoTimeout(1000); // Set the timeout to 5 seconds (5000 milliseconds)
                out = new PrintWriter(guestSocket.getOutputStream(), true); // Sending request to server
                in = new Scanner(guestSocket.getInputStream());
            }
            in = new Scanner(guestSocket.getInputStream());

            out = new PrintWriter(guestSocket.getOutputStream(), true);
            out.println(request);
            String response = "";
            // Receive response from server
            if (in.hasNextLine()) {
                response = in.nextLine();
                System.out.println("Server response: " + response);
            }
            // Handle response
            System.out.println();
            in.close();
            out.close();
            // Don't close socket here; it should be closed when done with all communication
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
