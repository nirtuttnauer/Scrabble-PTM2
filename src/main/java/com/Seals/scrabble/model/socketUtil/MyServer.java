package com.Seals.scrabble.model.socketUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyServer {
    private final int port;
    private final ClientHandler ch;
    private final AtomicBoolean stop;
    private final ExecutorService executorService;
    private final BlockingQueue<Socket> queue;

    public MyServer(int port, ClientHandler ch) {
        this.port = port;
        this.ch = ch;
        this.stop = new AtomicBoolean(false);
        this.executorService = Executors.newCachedThreadPool();
        this.queue = new LinkedBlockingQueue<>();
    }

    public void start() {
        stop.set(false);
        new Thread(this::startServer).start();
        new Thread(this::processConnections).start();
    }

    private void startServer() {
        try (ServerSocket server = new ServerSocket(port)) {
            server.setSoTimeout(1000);
            while (!stop.get()) {
                try {
                    Socket client = server.accept();
                    queue.put(client);
                } catch (SocketTimeoutException | InterruptedException ignored) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processConnections() {
        while (!stop.get() || !queue.isEmpty()) {
            try {
                Socket client = queue.take();
                executorService.execute(() -> {
                    try {
                        ch.handleClient(client.getInputStream(), client.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            ch.close();
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void close() {
        stop.set(true);
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public int getPort() {
        return port;
    }

    public ClientHandler getCh() {
        return ch;
    }

    public boolean isStop() {
        return stop.get();
    }
}
