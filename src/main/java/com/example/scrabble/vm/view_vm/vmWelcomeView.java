package com.example.scrabble.vm.view_vm;

import com.example.scrabble.model.Model;
import com.example.scrabble.vm.generics.IVM;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class vmWelcomeView extends vmView implements Observer, IVM{
    String nickname;
    Model model;

    public vmWelcomeView() {
        this.model = new Model();
        nickname = "hello";
    }

    @Override
    public void update(Observable o, Object arg) {

    }


}
