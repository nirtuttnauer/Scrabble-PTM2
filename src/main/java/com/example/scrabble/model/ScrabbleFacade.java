package com.example.scrabble.model;

public class ScrabbleFacade {
    private Model hostModel;
    private Model guestModel;

    public ScrabbleFacade() {
        hostModel = new HostModel();
        guestModel = new GuestModel();
    }

    public void setHostNickname(String nickname) {
        hostModel.setNickname(nickname);
    }

    public void setGuestNickname(String nickname) {
        guestModel.setNickname(nickname);
    }

    public String getHostNickname() {
        return hostModel.getNickname();
    }

    public String getGuestNickname() {
        return guestModel.getNickname();
    }

    public void createHostServer() {
        ((HostModel) hostModel).startServer();
    }

    public void closeHostServer() {
        ((HostModel) hostModel).stopServer();
    }

    public void sendRequestToHost(String request) {
        ((GuestModel) guestModel).sendRequestToHost(request);
    }

    // Other methods for interacting with the models and performing operations

    // ...
}
