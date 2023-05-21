package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.model.facades.ModelFacade;
import com.Seals.scrabble.model.facades.hModelFacade;
import com.Seals.scrabble.model.facades.iModelFacade;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;
import java.util.Observer;

public class ViewModel implements iViewModel, Observer {
    private iModelFacade modelFacade;
    StringProperty nickname;

    public ViewModel(iModelFacade modelFacade) {
        this.modelFacade = modelFacade;
        this.nickname = new SimpleStringProperty();
        modelFacade.addObserver(this);
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }

    @Override
    public void setGuestModel(String nickname, int port) {
        modelFacade.setNickname(nickname);
        // Set up the guest model with the provided nickname and port
    }

    public void setNickname(String nickname) {
        modelFacade.setNickname(nickname);
    }

    @Override
    public void testDMServerConnection() {
        if (modelFacade instanceof hModelFacade) {
            hModelFacade hModelFacade = (hModelFacade) modelFacade;
            hModelFacade.testDMServerConnection();
        } else {
            // Handle non-host model behavior
        }
    }

    public String getNickname() {
        return modelFacade.getNickname();
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void deleteObserver(Observer observer) {

    }

    @Override
    public void update(Observable o, Object arg) {
        // Update the ViewModel based on changes in the modelFacade
        if (o == modelFacade) {
            nickname.set(modelFacade.getNickname());
        }
    }

    void toggleModel() {
        if (modelFacade instanceof hModelFacade) {
            modelFacade = new ModelFacade();
        } else {
            modelFacade = new hModelFacade(modelFacade);
        }
    }

    public iModelFacade getModelFacade() {
        return modelFacade;
    }
}
