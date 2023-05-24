package com.Seals.scrabble;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;

public class MainModel {
    public static void main(String[] args) {
        //create host and it should join itself
        hModel hostmodel = new hModel(new Model());
        hostmodel.startServer();

        hostmodel.sendRequestToHost("p,5");
        //create guests... one should not be able to connect
        Model model = new Model();
        Model model2 = new Model();
        Model model3 = new Model();
        Model model4 = new Model();
        //start host server

        //player 1 connects;
        System.out.println("Player 1 should be able to connect");
        model.connectToHost();
        model.sendRequestToHost("p,5");
        //player 2 connects;
        System.out.println("Player 2 should be able to connect");
        model2.connectToHost();
        model2.sendRequestToHost("p,5");
        //player 3 connects;
        System.out.println("Player 3 should not be able to connect");
        model3.connectToHost();
        model3.sendRequestToHost("p,5");
        //player 4 connects;
        //should not be able to connect
        System.out.println("Player 4 should not be able to connect");
        model.connectToHost();
        model.sendRequestToHost("p,5");

        hostmodel.startGame();
//        hostmodel.performGameAction("Hello", 1);
        model.disconnectFromHost();
        model2.disconnectFromHost();
        model3.disconnectFromHost();
        model4.disconnectFromHost();
        hostmodel.stopServer();

    }
}
