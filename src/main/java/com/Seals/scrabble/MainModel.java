package com.Seals.scrabble;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;

import static com.Seals.scrabble.model.socketUtil.SocketUtil.delay;

public class MainModel {

    public static void main(String[] args) {
        delay(1000);

        // Create and start the host server
        hModel hostModel = new hModel(new Model());
        hostModel.startServer();

        // Create and connect guests
        Model[] models = {
                createAndConnectGuest(),
//                createAndConnectGuest(),
//                createAndConnectGuest(),
//                createAndConnectGuest()
        };

        hostModel.startGame();

        // Test sending a request from one of the players
//        if (models.length > 0) {
//            models[0].sendRequestToHost("PL", new String[]{String.valueOf(models[0].getID()), ",H,7,8,lolz"});
//        }

//        delay(1000);

//        hostModel.endGame();

        // Disconnect guests
//        disconnectGuests(models);

//        hostModel.stopServer();
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
