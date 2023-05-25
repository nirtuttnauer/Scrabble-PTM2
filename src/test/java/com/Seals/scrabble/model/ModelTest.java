package com.Seals.scrabble.model;

import com.Seals.scrabble.model.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ModelTest {
    private Model model;
    private final int serverPort = 8080;
    private final String serverAddress = "localhost";

    @BeforeEach
    public void setup() {
        model = new Model();
        model.setGuestModel(serverAddress, serverPort);
    }

    @Test
    public void testSendRequestToHost() throws IOException {
        // Start a simple server to receive the request
        startServer();

        String query = "Hello, server!";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Response from server\n".getBytes());

        // Replace System.out with a custom PrintStream
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Create a Socket object with the input/output streams
        Socket socket = new Socket(serverAddress, serverPort);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream());

        // Set the input/output streams for the model
        model.setGuestModel(socket.getInetAddress().getHostName(), socket.getPort());

        // Send the request
        out.println(query);
        out.flush();

        // Receive and process the response
        model.sendRequestToHost(query);

        // Restore System.out
        System.setOut(originalOut);

        // Verify the response
        String response = outputStream.toString().trim();
        Assertions.assertEquals("Response from server: Response from server", response, "Response should match the expected value");

        // Close the resources
        out.close();
        in.close();
        socket.close();
    }

    private void startServer() {
        Thread serverThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(serverPort);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String query = in.readLine();
                out.println(query);
                out.flush();

                in.close();
                out.close();
                clientSocket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }
}
