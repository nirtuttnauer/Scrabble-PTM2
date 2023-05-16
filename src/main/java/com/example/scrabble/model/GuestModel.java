package com.example.scrabble.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static com.example.scrabble.model.SocketUtil.finallyClose;

public class GuestModel implements iModel {
    private String serverAddress;
    private int serverPort;

    public GuestModel() {
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
            String response = in.next();
            // Process the response from the host as needed
        } catch (IOException e) {
            // Handle any IO exceptions
            e.printStackTrace();
        } finally {
            finallyClose(client, out, in);
        }
    }
}
