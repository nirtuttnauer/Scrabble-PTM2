package com.example.scrabble.model;

public class Model implements iModel {
    String nickname;

    @Override
    public void setNickname(String name) {
        nickname = name;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public void serverStart() {
        System.out.println("serverStart() was not implemented for Model");
    }
}
