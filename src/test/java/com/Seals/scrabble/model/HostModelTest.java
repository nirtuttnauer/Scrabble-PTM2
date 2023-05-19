package com.Seals.scrabble.model;

import com.Seals.scrabble.model.serverSide.handler.BookScrabbleHandler;
import com.Seals.scrabble.model.socketUtil.MyServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HostModelTest {

    private hModel hostModel;
    private MyServer server;
    private int serverPort;
    private Thread serverThread;

    @BeforeEach
    void setUp() {
        Random r = new Random();
        int port = 6000 + r.nextInt(1000);
        server = new MyServer(port, new BookScrabbleHandler());
        serverPort = server.getPort();
        //needs fixing: should be empty ctor
        hostModel = new hModel(new Model());
    }

    @AfterEach
    void tearDown() {
        if (serverThread != null && serverThread.isAlive()) {
            server.close();
            try {
                serverThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hostModel = null;
    }

    @Test
    void testStartAndStopServer() {
        assertNull(hostModel.getGameServer());
        hostModel.startServer();
        assertNotNull(hostModel.getGameServer());
        assertTrue(!hostModel.getGameServer().isStop());

        hostModel.stopServer();
        assertNull(hostModel.getGameServer());
    }

//    @Test
//    void testHandleClientRequest() {
//        // Start the server
//        serverThread = new Thread(() -> {
//            server.start();
//        });
//        serverThread.start();
//
//        // Connect a client socket and send a request
//        Socket clientSocket = null;
//        try {
//            clientSocket = new Socket("localhost", serverPort);
//            InputStream input = new ByteArrayInputStream("test request".getBytes());
//            OutputStream output = new ByteArrayOutputStream();
////            hostModel.handleClientRequest(clientSocket);
//
//            // Verify the response
//            String response = output.toString();
//            assertEquals("expected response", response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (clientSocket != null) {
//                try {
//                    clientSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    @Test
    void testSendRequestToServer() {
        // Start the server
        serverThread = new Thread(() -> {
            server.start();
        });
        serverThread.start();

        // Send a request to the server
        String query = "test query";
        String response = hostModel.sendRequestToServer(query);

        // Verify the response
        assertEquals("expected response", response);
    }
}
