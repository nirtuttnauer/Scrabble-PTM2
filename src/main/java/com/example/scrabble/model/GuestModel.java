package com.example.scrabble.model;

import com.example.scrabble.server.MyServer;

import java.util.Observable;

public class GuestModel extends Observable implements iModel{
    private Client guest;
    private MyServer server;


    public GuestModel(Client guest, MyServer server) {
        this.guest = guest;
        this.server = server;
    }

    @Override
    public boolean checkClient() {
        return guest.isActive();
    }

    @Override
    public void increaseScore(int value) throws IllegalArgumentException{
        if(value<0)
            throw new IllegalArgumentException("Increment value must be positive!");
        guest.setScore(value);
    }

    @Override
    public void decreaseScore(int value) throws IllegalArgumentException{
    if(guest.getScore()<=0 || value<0)
        throw new IllegalArgumentException("Decrement value must be positive!");
    guest.setScore(-value); // decrease the score
    }
}
