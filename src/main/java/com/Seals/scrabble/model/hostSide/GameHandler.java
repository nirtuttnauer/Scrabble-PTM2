package com.Seals.scrabble.model.hostSide;

import com.Seals.scrabble.model.socketUtil.ClientHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameHandler implements ClientHandler {

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        Scanner scanner = new Scanner(inFromClient);
        PrintWriter writer = new PrintWriter(outToClient, true); // Auto-flush enabled

        while (scanner.hasNextLine()) {
            String request = scanner.nextLine();

            // Process the request and generate a response
            String response = processRequest(request);

            // Send the response back to the client
            writer.println(response);
        }
    }

    @Override
    public void close() {
        // Cleanup or additional operations when the handler is closed
    }

    private String processRequest(String request) {
        // Implement your logic to process the request and generate a response
        // Example: Echo the request as the response
        return request;
    }
}
