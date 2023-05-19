package com.Seals.scrabble;

import com.Seals.scrabble.MainServer;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainServerTest {
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private MainServer mainServer;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        mainServer = new MainServer();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    void testStartServerAndStopServer() {
        // Start the server
        mainServer.startServer();
        String startServerOutput = outputStreamCaptor.toString().trim();
        assertEquals("Server started on port: " + MainServer.getServer().getPort(), startServerOutput);

        // Stop the server
        mainServer.stopServer();
        String stopServerOutput = outputStreamCaptor.toString().trim();
        assertEquals("Server closed on port: " + MainServer.getServer().getPort(), stopServerOutput);
    }
}
