package com.Seals.scrabble.vm.generics;

import com.Seals.scrabble.model.HostModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HostVM extends VM{
    private final HostModel model;
    private final StringProperty welcomeMessage;

    public HostVM() {

        this.model = new HostModel();
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

    public void testDMServerConnection() {
        model.testDMServerConnection();
    }
}
