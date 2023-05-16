//package com.example.scrabble.server;
//
//import org.junit.Test;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//import static org.junit.Assert.*;
//
//public class MyServerTest {
//
//    @Test
//    public void testStartServer() throws IOException {
//        // Create a test ClientHandler
//        ClientHandler clientHandler = new ClientHandler() {
//            @Override
//            public void handleClient(InputStream in, OutputStream out) throws IOException {
//                // Do nothing for the test
//            }
//
//            @Override
//            public void close() throws IOException {
//                // Do nothing for the test
//            }
//        };
//
//        // Create a test ServerSocket
//        TestServerSocket serverSocket = new TestServerSocket();
//
//        // Create an instance of MyServer
//        MyServer server = new MyServer(1234, clientHandler);
//        server.setServerSocket(serverSocket);
//
//        // Start the server
//        server.start();
//
//        // Verify that the server is running
//        assertTrue(server.isRunning());
//
//        // Verify that the ServerSocket is created and set with the correct timeout
//        assertEquals(1000, serverSocket.getSoTimeout());
//
//        // Simulate accepting a client connection
//        TestSocket clientSocket = new TestSocket();
//        serverSocket.setAcceptedSocket(clientSocket);
//
//        // Verify that the ClientHandler handles the client
//        serverSocket.triggerAccept(); // Trigger the server to accept the client connection
//
//        // Verify that the ClientHandler handles the client by checking the input and output streams
//        assertTrue(clientSocket.getInputStream().isHandled());
//        assertTrue(clientSocket.getOutputStream().isHandled());
//
//        // Verify that the ClientHandler is closed
//        clientHandler.close();
//
//        // Verify that the client socket is closed
//        assertTrue(clientSocket.isClosed());
//
//        // Stop the server
//        server.stop();
//
//        // Verify that the server is stopped
//        assertFalse(server.isRunning());
//
//        // Verify that the ServerSocket is closed
//        assertTrue(serverSocket.isClosed());
//    }
//
//    private static class TestServerSocket extends ServerSocket {
//        private int soTimeout;
//        private Socket acceptedSocket;
//
//        public TestServerSocket() throws IOException {
//            // Do nothing for the test
//        }
//
//        @Override
//        public void setSoTimeout(int timeout) throws SocketException {
//            soTimeout = timeout;
//        }
//
//        @Override
//        public int getSoTimeout() throws IOException {
//            return soTimeout;
//        }
//
//        @Override
//        public Socket accept() throws IOException {
//            return acceptedSocket;
//        }
//
//        public void setAcceptedSocket(Socket socket) {
//            acceptedSocket = socket;
//        }
//
//        public void triggerAccept() {
//            // Do nothing for the test
//        }
//    }
//
//    private static class TestSocket extends Socket {
//        private final TestInputStream inputStream = new TestInputStream();
//        private final TestOutputStream outputStream = new TestOutputStream();
//        private boolean closed;
//
//        @Override
//        public InputStream getInputStream() throws IOException {
//            return inputStream;
//        }
//
//        @Override
//        public OutputStream getOutputStream() throws IOException {
//            return outputStream;
//        }
//
//        @Override
//        public void close() throws IOException {
//            closed = true;
//        }
//
//        public boolean isClosed() {
//            return closed;
//        }
//    }
//
//    private static class TestInputStream extends InputStream {
//        private boolean handled;
//
//        @Override
//        public int read() throws IOException {
//            return -1;
//        }
//
//        public void setHandled(boolean handled) {
//            this.handled = handled;
//        }
//
//        public boolean isHandled() {
//            return handled;
//        }
//    }
//
//    private static class TestOutputStream extends OutputStream {
//        private boolean handled;
//
//        @Override
//        public void write(int b) throws IOException {
//
//        }
//
//        public void setHandled(boolean handled) {
//            this.handled = handled;
//        }
//
//        public boolean isHandled() {
//            return handled;
//        }
//    }
//
//}
//
//
//
