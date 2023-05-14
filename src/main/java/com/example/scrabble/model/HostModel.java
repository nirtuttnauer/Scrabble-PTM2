package com.example.scrabble.model;

import com.example.scrabble.server.MyServer;

import java.util.Observable;

public class HostModel extends Observable implements iModel {
    MyServer server; // use myServer to get the game server
    Client host; // connect the client that host the game


    public HostModel(MyServer server, Client host) {
        this.server = server;
        this.host = host;
    }


    @Override
    public boolean checkClient() {
        return host.isActive();
    }

    @Override
    public void increaseScore(int value) throws IllegalArgumentException{
        if(value<0)
            throw new IllegalArgumentException("Increment value must be positive!");
        host.setScore(value);
    }

    @Override
    public void decreaseScore(int value) throws IllegalArgumentException{
        if(host.getScore()<=0 || value<0)
            throw new IllegalArgumentException("Decrement value must be positive!");
        host.setScore(-value); // decrease the score
    }
}


// Define properties and methods representing the application's data and behavior
// For example:
//    private String welcomeMessage;
//
//    public HostModel() {
//        welcomeMessage = "";
//    }
//
//    public void setWelcomeMessage(String message) {
//        welcomeMessage = message;
//    }
//
//    public String getWelcomeMessage() {
//        return welcomeMessage;
//    }

