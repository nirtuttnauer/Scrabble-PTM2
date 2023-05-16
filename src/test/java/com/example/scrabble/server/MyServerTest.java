package com.example.scrabble.server;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MyServerTest {

    @Test
    public void testStartServer() throws IOException {
        // Create a test ClientHandler
        ClientHandler clientHandler = new ClientHandler() {
            @Override
            public void handleClient(InputStream in, OutputStream out) {
                // Do nothing for the test
            }

            @Override
            public void close() {
                // Do nothing for the test
            }
        };

        // Create an instance of MyServer
        MyServer server = new MyServer(1234, clientHandler);

        // Start the server
        server.start();

        // Verify that the server is running
        assertFalse(server.isStop());

        // Simulate accepting a client connection
        TestSocket clientSocket = new TestSocket();
        server.getCh().handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());

        // Verify that the ClientHandler handles the client by checking the input and output streams
        // Add your assertions here

        // Verify that the ClientHandler is closed
        server.getCh().close();

        // Stop the server
        server.close();

        // Verify that the server is stopped
        assertTrue(server.isStop());
    }

    private static class TestSocket extends Socket {
        private final TestInputStream inputStream = new TestInputStream();
        private final TestOutputStream outputStream = new TestOutputStream();
        private boolean closed;

        @Override
        public InputStream getInputStream() throws IOException {
            return inputStream;
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return outputStream;
        }

        @Override
        public void close() throws IOException {
            closed = true;
        }

        public boolean isClosed() {
            return closed;
        }
    }

    private static class TestInputStream extends InputStream {
        private boolean handled;

        @Override
        public int read() throws IOException {
            return -1;
        }

        public void setHandled(boolean handled) {
            this.handled = handled;
        }

        public boolean isHandled() {
            return handled;
        }
    }

    private static class TestOutputStream extends OutputStream {
        private boolean handled;

        @Override
        public void write(int b) throws IOException {

        }

        public void setHandled(boolean handled) {
            this.handled = handled;
        }

        public boolean isHandled() {
            return handled;
        }
    }
}
