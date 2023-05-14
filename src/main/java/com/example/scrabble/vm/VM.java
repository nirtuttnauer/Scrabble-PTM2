package com.example.scrabble.vm;

import com.example.scrabble.model.Model;
import com.example.scrabble.model.iModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VM {
    private final Model model;
    private final StringProperty nickname;

    public VM(iModel model) {
        this.model = (Model) model;
        nickname = new SimpleStringProperty();
    }

    public void setNickname(String nickname) {
        this.nickname.set(nickname);
        model.setNickname(nickname);
    }

    public String getNickname() {
        return model.getNickname();
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }

    public void serverStart() {
        model.serverStart();
    }
}