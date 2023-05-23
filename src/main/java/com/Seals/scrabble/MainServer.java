package com.Seals.scrabble;

import com.Seals.scrabble.model.serverSide.manager.BookScrabbleHandler;
import com.Seals.scrabble.model.socketUtil.MyServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainServer {
    private static MyServer DMServer;

    public static void main(String[] args) {
        MainServer mainServer = new MainServer();
        mainServer.startServer();

        Thread thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (!DMServer.isStop()) {
                String input = scanner.nextLine();
                if (input.equals("stop")) {
                    mainServer.stopServer();
                    break;
                }
            }
        });

        thread.start();
    }

    public void startServer() {
        DMServer = new MyServer(Settings.getDMServerPort(), new CustomBookScrabbleHandler());
        DMServer.start();
        System.out.println("Server started on port: " + DMServer.getPort());
    }

    public void stopServer() {
        DMServer.close();
        System.out.println("Server closed on port: " + DMServer.getPort());
    }

    public static MyServer getServer() {
        return DMServer;
    }

    // Custom BookScrabbleHandler that extends BookScrabbleHandler
    public static class CustomBookScrabbleHandler extends BookScrabbleHandler {
// Inside the handleClient method of the CustomBookScrabbleHandler class

        @Override
        public void handleClient(InputStream inFromClient, OutputStream outToClient) {
                String request = "";
            // Implement the logic to handle the client request
            try (Scanner scanner = new Scanner(inFromClient)) {
                if (scanner.hasNextLine()) {
                    request = scanner.nextLine();
                    System.out.println("Received request from client: " + request);
                    // Process the request as needed
                } else {
                    System.out.println("No request received from client.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error reading client request: " + e.getMessage());
            }
            try (PrintWriter out = new PrintWriter(outToClient)) {
                // Send the response to the client
                out.println("Response to client request" + request);

            }
        }


        @Override
        public void close() {
            // Implement the cleanup logic if needed
        }
    }
}
