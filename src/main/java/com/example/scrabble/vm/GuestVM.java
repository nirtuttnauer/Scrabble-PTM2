package com.example.scrabble.vm;

import com.example.scrabble.model.GuestModel;
import com.example.scrabble.model.HostModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GuestVM implements iVM{
    private final GuestModel model;
    private final StringProperty welcomeMessage;

    public GuestVM(GuestModel model) {
        this.model = model;
        welcomeMessage = new SimpleStringProperty();
    }

    public StringProperty welcomeMessageProperty() {
        return welcomeMessage;
    }

    public void onHelloButtonClick() {
        model.setWelcomeMessage("Lama Omer Kaha Kaved?!");
        welcomeMessage.set(model.getWelcomeMessage());
    }

    public void onJoinButtonClick() {
        model.setWelcomeMessage("Lama");
        welcomeMessage.set(model.getWelcomeMessage());
    }
}
