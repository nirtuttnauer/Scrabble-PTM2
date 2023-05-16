package com.example.scrabble.vm.view_vm;

import com.example.scrabble.model.ScrabbleFacade;
import com.example.scrabble.vm.generics.IVM;

import java.util.Observable;
import java.util.Observer;

public class vmWelcomeView extends vmView implements Observer, IVM{
    String nickname;
    ScrabbleFacade model;

    public vmWelcomeView() {
        this.model = new ScrabbleFacade();
        nickname = "hello";
    }

    @Override
    public void update(Observable o, Object arg) {

    }


}
