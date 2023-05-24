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
        DMServer = new MyServer(Settings.getDMServerPort(), new BookScrabbleHandler());
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

}
