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

public class MyServer implements Runnable{
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
        new Thread(this).start();
        new Thread(this::processConnections).start();
    }

    private void processConnections() {
        while (!stop.get() || !queue.isEmpty()) {
            try {
                Socket client = queue.take();
                System.out.println(client.getInetAddress().getHostAddress() + ":" + client.getPort());
                String clientKey = client.getInetAddress().getHostAddress() + ":" + client.getPort();

                clientHandlers.putIfAbsent(clientKey, createHandler(ch,client));
                ClientHandler clientHandler = clientHandlers.get(clientKey);

                executorService.execute(() -> {
                    try {
                        clientHandler.handleClient(client.getInputStream(), client.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    finally {
////                        clientHandler.close();
////                        try {
////                            client.close();
////                        } catch (IOException e) {
////                            throw new RuntimeException(e);
////                        }
//                    }
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
//            clientHandler.close();
        }
    }

    public int getPort() {
        return port;
    }

    public boolean isStop() {
        return stop.get();
    }

    public String broadcast(String message) {
        System.out.println("line 107 " + clientHandlers.values().size());
        for (ClientHandler clientHandler : clientHandlers.values()) {
            try {
                clientHandler.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return message;
    }

    public ClientHandler createHandler(ClientHandler handler,Socket socket) {
        if (handler instanceof GameHandler) {
            return new GameHandler(socket);
        } else if (handler instanceof BookScrabbleHandler) {
            return new BookScrabbleHandler();
        } else {
            throw new IllegalArgumentException("Unsupported handler type: " + handler.getClass().getName());
        }
    }

    @Override
    public void run() {
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
}
