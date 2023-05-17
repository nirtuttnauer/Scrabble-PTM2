
package com.Seals.scrabble.vm.view_vm;

import com.Seals.scrabble.vm.generics.IVM;

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
