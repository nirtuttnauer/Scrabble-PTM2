package com.example.scrabble.vm;

import com.example.scrabble.model.iModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StartVM implements iVM{
    private final iModel model;
    private final StringProperty welcomeMessage;

    public StartVM(iModel model) {
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

    public void onSettingButtonClick() {

    }
}
