package com.example.scrabble.model;

import java.util.Observable;

public class Model extends Observable implements iModel {
    String nickname;

    public void setNickname(String name) {
        nickname = name;
    }

    public String getNickname() {
        return nickname;
    }


}
