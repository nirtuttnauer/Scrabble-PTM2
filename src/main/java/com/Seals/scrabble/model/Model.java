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
    private Socket guestSocket;
    private PrintWriter out = null;
    private Scanner in = null;

    public Model() {
        this.nickname = new SimpleStringProperty();
        hostPort = Settings.getHostServerPort();
    }

    public Model(iModel model)  {

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
            System.out.println("Connecting to host on port" + hostPort);
            ((Model)this).guestSocket = new Socket(serverAddress, Settings.getHostServerPort());
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


    public void sendRequestToHost(String query) {
        try {
            out = new PrintWriter(guestSocket.getOutputStream());
            in = new Scanner(guestSocket.getInputStream());

            out.println(query);
            out.flush();
            String response = in.nextLine(); // Use nextLine() instead of next() to read the complete response
            // Process the response from the host as needed
            System.out.println("Response from server: " + response);
        } catch (IOException e) {
            // Handle any IO exceptions
            e.printStackTrace();
        }
    }

    // Rest of the Model code...
}
