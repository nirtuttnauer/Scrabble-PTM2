package com.example.scrabble.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;

public class Model extends Observable implements iModel {
    private StringProperty nickname;

    public Model() {
        nickname = new SimpleStringProperty();
    }

    public void setNickname(String name) {
        nickname.set(name);
    }

    public String getNickname() {
        return nickname.get();
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }
}
