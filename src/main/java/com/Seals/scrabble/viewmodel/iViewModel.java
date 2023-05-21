package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.model.facades.iModelFacade;
import javafx.beans.value.ObservableValue;

import java.util.Observer;

public interface iViewModel extends Observer {
    ObservableValue<String> nicknameProperty();

    void setGuestModel(String nickname, int port);

    void setNickname(String nickname);

    void testDMServerConnection();

    String getNickname();

    void addObserver(java.util.Observer observer);

    void deleteObserver(java.util.Observer observer);

    iModelFacade getModelFacade();
}
