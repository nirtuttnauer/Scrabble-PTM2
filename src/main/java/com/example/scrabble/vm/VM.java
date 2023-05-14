package com.example.scrabble.vm;

import com.example.scrabble.model.iModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VM implements iVM{
    private final iModel model;
    private final StringProperty welcomeMessage;

    public VM(iModel model) {
        this.model = model;
        welcomeMessage = new SimpleStringProperty();
    }

    @Override
    public void setNickname(String String) {
        model.setNickname(String);
    }

    @Override
    public void serverStart() {
        System.out.println("this cannot start a server");
    }
}
