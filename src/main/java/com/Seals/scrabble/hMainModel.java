package com.Seals.scrabble;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;

import static com.Seals.scrabble.model.socketUtil.SocketUtil.delay;

public class hMainModel {

    public static void main(String[] args) {
        delay(1000);

        // Create and start the host server
        hModel hostModel = new hModel(new Model());
        hostModel.startServer();
        delay(10000);
        // Create and connect guests
        hostModel.startGame();
    }

    private static Model createAndConnectGuest() {
        Model model = new Model();
        model.connectToHost();
        model.sendRequestToHost("NP", null); // Request for ID of new player
        return model;
    }

    private static void disconnectGuests(Model... models) {
        for (Model model : models) {
            model.disconnectFromHost();
        }
    }
}
