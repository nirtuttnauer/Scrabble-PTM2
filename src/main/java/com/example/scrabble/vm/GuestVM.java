package com.example.scrabble.vm;

import com.example.scrabble.model.GuestModel;
import com.example.scrabble.model.HostModel;
import com.example.scrabble.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GuestVM extends VM {
    private final Model model;
    private final StringProperty welcomeMessage;

    public GuestVM(GuestModel model) {
        super(model);
        this.model = new GuestModel();
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
