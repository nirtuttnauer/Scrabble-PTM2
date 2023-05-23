package com.Seals.scrabble;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;

public class MainModel {
    public static void main(String[] args) {
        hModel hostmodel = new hModel(new Model());
        Model model = new Model();
        hostmodel.startServer();
        model.sendRequestToHost("Hello");

        hostmodel.startGame();
        hostmodel.performGameAction("Hello",2);

    }
}
