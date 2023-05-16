package com.example.scrabble.vm.generics;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import com.example.scrabble.model.HostModel;

public class HostVM extends VM{
    private final HostModel model;
    private final StringProperty welcomeMessage;

    public HostVM(HostModel model) {

        this.model = model;
        welcomeMessage = new SimpleStringProperty();
    }
    @Override
    public void setNickname(String String) {
        model.setNickname(String);
    }

    public void startServer() {
        model.startServer();
    }
    public void closeServer() {
        model.stopServer();
    }
}
