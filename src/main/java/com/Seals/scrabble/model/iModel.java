package com.Seals.scrabble.model;

import javafx.beans.property.StringProperty;

public interface iModel {
    void setNickname(String name);

    String getNickname();

    StringProperty nicknameProperty();
}
