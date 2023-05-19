package com.Seals.scrabble.model;

import com.Seals.scrabble.model.socketUtil.SocketUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;

public class Model extends Observable implements iModel {
    private StringProperty nickname = new SimpleStringProperty();
    private String serverAddress = "localhost";
    private int serverPort;

    public Model() {
    }

    public Model(iModel model) {
        this.nickname = model.nicknameProperty();
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

    public void setGuestModel(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void sendRequestToHost(String query) {
        Socket client = null;
        PrintWriter out = null;
        Scanner in = null;

        try {
            client = new Socket(serverAddress, serverPort);
            out = new PrintWriter(client.getOutputStream());
            in = new Scanner(client.getInputStream());

            out.println(query);
            out.flush();
            String response = in.nextLine(); // Use nextLine() instead of next() to read the complete response
            // Process the response from the host as needed
            System.out.println("Response from server: " + response);
        } catch (IOException e) {
            // Handle any IO exceptions
            e.printStackTrace();
        } finally {
            SocketUtil.finallyClose(client, out, in);
        }
    }

    // Rest of the Model code...
}
