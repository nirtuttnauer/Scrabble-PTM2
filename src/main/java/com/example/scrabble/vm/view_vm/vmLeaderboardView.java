package com.example.scrabble.vm.view_vm;

import com.example.scrabble.vm.generics.IVM;

import java.util.Observable;
import java.util.Observer;

public class vmLeaderboardView extends vmView implements Observer, IVM {
    public vmLeaderboardView() {
        super();
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
