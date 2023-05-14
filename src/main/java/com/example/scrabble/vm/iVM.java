package com.example.scrabble.vm;

import com.example.scrabble.model.iModel;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public interface iVM {
    void setNickname(String String);

    public String getNickname();

    public StringProperty nicknameProperty();



    void serverStart();
}
