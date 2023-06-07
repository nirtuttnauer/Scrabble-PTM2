package com.Seals.scrabble.model.socketUtil;

import com.Seals.scrabble.model.hostSide.GameHandler;
import com.Seals.scrabble.model.serverSide.manager.BookScrabbleHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyServer {
    private final int port;
    private final Map<String, ClientHandler> clientHandlers;
    private final ClientHandler ch;
    private final AtomicBoolean stop;
    private final ExecutorService executorService;
    private final BlockingQueue<Socket> queue;

    public MyServer(int port, ClientHandler ch) {
        this.port = port;
        this.clientHandlers = new ConcurrentHashMap<>();
        this.stop = new AtomicBoolean(false);
        this.executorService = Executors.newCachedThreadPool();
        this.queue = new LinkedBlockingQueue<>();
        this.ch = ch;
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
                String clientKey = client.getInetAddress().getHostAddress() + ":" + client.getPort();

                clientHandlers.putIfAbsent(clientKey, createHandler(ch));
                ClientHandler clientHandler = clientHandlers.get(clientKey);

                executorService.execute(() -> {
                    try {
                        clientHandler.handleClient(client.getInputStream(), client.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        clientHandler.close();
                        try {
                            client.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
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

        for (ClientHandler clientHandler : clientHandlers.values()) {
            clientHandler.close();
        }
    }

    public int getPort() {
        return port;
    }

    public boolean isStop() {
        return stop.get();
    }

    public void broadcast(String message) {
        for (ClientHandler clientHandler : clientHandlers.values()) {
            clientHandler.sendMessage(message);
        }
    }
 public ClientHandler createHandler(ClientHandler handler) {
        if (handler instanceof GameHandler) {
            return new GameHandler();
        } else if (handler instanceof BookScrabbleHandler) {
            return new BookScrabbleHandler();
        } else {
            throw new IllegalArgumentException("Unsupported handler type: " + handler.getClass().getName());
        }
    }

}
