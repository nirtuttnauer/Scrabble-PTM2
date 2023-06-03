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
                createAndConnectGuest(),
                createAndConnectGuest(),
                createAndConnectGuest()
        };

        hostModel.startGame();

        // Test sending a request from one of the players
        if (models.length > 0) {
            models[0].sendRequestToHost("PL," + models[0].getID() + ",H,7,8,lolz");
        }

        delay(1000);

        hostModel.endGame();

        // Disconnect guests
        disconnectGuests(models);

        hostModel.stopServer();
    }

    private static Model createAndConnectGuest() {
        Model model = new Model();
        model.connectToHost();

        // Send a request to host and get an ID
        String response = model.sendRequestToHost("NP"); // Request for ID of new player
        int id = parseIdFromResponse(response);

        if (id > 0) {
            model.setID(id);
        } else {
            System.out.println("Failed to retrieve a valid player ID from the host.");
        }

        return model;
    }

    private static void disconnectGuests(Model... models) {
        for (Model model : models) {
            model.disconnectFromHost();
        }
    }



    // Assumes the ID is an integer returned as a response.
    private static int parseIdFromResponse(String response) {
        if (response != null && response.matches("\\d+")) {
            return Integer.parseInt(response);
        } else {
            return 0;
        }
    }
}
