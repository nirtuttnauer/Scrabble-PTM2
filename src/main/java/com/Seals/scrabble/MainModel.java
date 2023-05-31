package com.Seals.scrabble;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;

public class MainModel {

    public static void main(String[] args) {
        delay(1000);

        hModel hostmodel = new hModel(new Model());
        hostmodel.startServer();

        // Create and connect guests
        Model model = createAndConnectGuest();
        Model model2 = createAndConnectGuest();
        Model model3 = createAndConnectGuest();
        Model model4 = createAndConnectGuest();

        hostmodel.startGame();
        model.sendRequestToHost("PL," + model.getID() + ",lolz");

        delay(1000);

        hostmodel.endGame();
        disconnectGuests(model, model2, model3, model4);
        hostmodel.stopServer();
    }

    private static Model createAndConnectGuest() {
        Model model = new Model();
        model.connectToHost();

        // Send a request to host and get an ID
        String response = model.sendRequestToHost("NP"); // Request for ID of new player
        int id = parseIdFromResponse(response);
        model.setID(id);

        return model;
    }

    private static void disconnectGuests(Model... models) {
        for (Model model : models) {
            model.disconnectFromHost();
        }
    }

    private static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Assumes the ID is an integer returned as a response.
    public static int parseIdFromResponse(String response) {
        if (response.matches("\\d+")) {
            return Integer.parseInt(response);
        } else {
        return 0;
        }
    }

}
