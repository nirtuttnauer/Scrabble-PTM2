package com.example.scrabble.model;

public interface iModel  {
    boolean checkClient(); // checking if the client is ready to play
    void increaseScore(int value);
    void decreaseScore(int value);

}
