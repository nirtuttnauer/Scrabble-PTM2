package com.Seals.scrabble.viewmodel;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.util.Observer;

public interface iViewModel extends Observer {
    ObservableValue<String> nicknameProperty();

    void setNickname(String nickname);

   String getNickname();

    void addObserver(java.util.Observer observer);

    void deleteObserver(java.util.Observer observer);

   StringProperty getHandProperty();


}
