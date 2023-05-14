package com.example.scrabble.vm;

import com.example.scrabble.model.iModel;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public interface iVM extends Observable {
    void setNickname(String String);

    public String getNickname();

    public StringProperty nicknameProperty();



    void serverStart();
}
