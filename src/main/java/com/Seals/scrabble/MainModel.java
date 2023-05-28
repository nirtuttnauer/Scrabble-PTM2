package com.Seals.scrabble;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;

import static java.lang.Thread.sleep;

public class MainModel {
    public static void main(String[] args) {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //create host and it should join itself
        hModel hostmodel = new hModel(new Model());
        System.out.println("------------------");

        hostmodel.startServer();

        System.out.println("------------------");

        //create guests... one should not be able to connect
        Model model = new Model();
        Model model2 = new Model();
        Model model3 = new Model();
        Model model4 = new Model();
        //start host server

        //player 1 connects;
//        System.out.println("Player 1 should be able to connect");
        model.connectToHost();
        int id = Integer.parseInt(model.sendRequestToHost("N"));
        model.setID(id);
        System.out.println("------------------");

        //player 2 connects;
//        System.out.println("Player 2 should be able to connect");
        model2.connectToHost();
        model2.sendRequestToHost("N");
        id = Integer.parseInt(model2.sendRequestToHost("N"));
        model2.setID(id);
        System.out.println("------------------");

        //player 3 connects;
//        System.out.println("Player 3 should be be able to connect");
        model3.connectToHost();
        model3.sendRequestToHost("N");
        id = Integer.parseInt(model3.sendRequestToHost("N"));
        model3.setID(id);
        System.out.println("------------------");

        //player 4 connects;
        //should not be able to connect
//        System.out.println("Player 4 should not be able to connect");
        model4.connectToHost();
        model4.sendRequestToHost("N");
        id = Integer.parseInt(model4.sendRequestToHost("N"));
        model4.setID(id);
        System.out.println("------------------");


        hostmodel.startGame();

        model.sendRequestToHost("PL," + model.getID() + ",lolz");

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        hostmodel.endGame();
//        hostmodel.performGameAction("Hello", 1);
        model.disconnectFromHost();
        model2.disconnectFromHost();
        model3.disconnectFromHost();
        model4.disconnectFromHost();
        hostmodel.stopServer();

    }
}
