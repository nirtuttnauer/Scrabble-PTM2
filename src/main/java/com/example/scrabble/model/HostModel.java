package com.example.scrabble.model;

import com.example.scrabble.server.MyServer;
import com.example.scrabble.server.managers.BookScrabbleHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import static com.example.scrabble.model.SocketUtil.finallyClose;

public class HostModel implements iModel {
    private MyServer server;

    public HostModel() {
        Random r = new Random();
        int port = 6000 + r.nextInt(1000);
        server = new MyServer(port, new BookScrabbleHandler());
    }

    public void startServer() {
        server.start(); // Start the server
        System.out.println("Server started on port " + server.getPort());
    }

    public void stopServer() {
        server.close(); // Stop the server
        System.out.println("Server closed on port " + server.getPort());
    }

    public void handleClientRequest(Socket client) {
        try {
            server.getCh().handleClient(client.getInputStream(), client.getOutputStream()); // Delegate the client handling to the server's client handler
        } catch (IOException e) {
            System.out.println("Error handling client request: " + e.getMessage());
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    public String sendRequestToServer(String query) {
        Socket client = null;
        PrintWriter out = null;
        Scanner in = null;
        String response = "";

        try {
            client = new Socket("localhost", server.getPort());
            out = new PrintWriter(client.getOutputStream());
            in = new Scanner(client.getInputStream());

            out.println(query);
            out.flush();
            response = in.next();
        } catch (IOException e) {
            System.out.println("Your code ran into an IOException: " + e.getMessage());
        } finally {
            finallyClose(client, out, in);
        }

        return response;
    }


}
