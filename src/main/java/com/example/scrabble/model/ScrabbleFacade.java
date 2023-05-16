package com.example.scrabble.model;

import java.util.Observable;

public class ScrabbleFacade extends Observable {
    private ModelData modelData;
    private HostModel hostModel;
    private GuestModel guestModel;

    public ScrabbleFacade() {
        hostModel = new HostModel();
        guestModel = new GuestModel();
        modelData = new ModelData();
    }

    public void sendRequestToHost(String request) {
        guestModel.sendRequestToHost(request);
    }

    public String getNickname() {
        return modelData.getNickname();
    }

    public void setNickname(String nickname) {
        modelData.setNickname(nickname);
    }

    public void startServer() {
         hostModel.startServer();
    }

    public void stopServer() {
        hostModel.stopServer();
    }

    // Other methods for interacting with the models and performing operations

    // ...
}
