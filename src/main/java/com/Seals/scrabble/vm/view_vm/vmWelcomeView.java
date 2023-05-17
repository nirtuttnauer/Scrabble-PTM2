package com.Seals.scrabble.vm.view_vm;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.vm.generics.IVM;

import java.util.Observable;
import java.util.Observer;

public class vmWelcomeView extends vmView implements Observer, IVM {
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
