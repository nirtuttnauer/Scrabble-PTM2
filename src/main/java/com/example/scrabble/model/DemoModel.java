package com.example.scrabble.model;

//this model is only for demo purposes and development testing.
public class DemoModel implements iModel{
    private String welcomeMessage;

    public DemoModel() {
        welcomeMessage = "";
    }

    public void setWelcomeMessage(String message) {
        welcomeMessage = message;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    @Override
    public boolean checkClient() {
        return false;
    }

    @Override
    public void increaseScore(int value) {

    }

    @Override
    public void decreaseScore(int value) {

    }
}
