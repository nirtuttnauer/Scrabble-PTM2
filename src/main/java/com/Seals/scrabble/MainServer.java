package com.Seals.scrabble;

import com.Seals.scrabble.model.serverSide.manager.BookScrabbleHandler;
import com.Seals.scrabble.model.socketUtil.MyServer;

public class MainServer {
    MyServer DMServer;
    public static void main(String[] args) {
        MyServer DMServer = new MyServer(Settings.getDefaultPort(), new BookScrabbleHandler());
        DMServer.start();
        System.out.println("Server started on port: " + DMServer.getPort());
        DMServer.close();
        System.out.println("Server closed on port: " + DMServer.getPort());
    }
}
