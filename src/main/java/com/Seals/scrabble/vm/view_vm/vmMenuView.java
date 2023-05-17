package com.Seals.scrabble.vm.view_vm;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.vm.generics.IVM;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;
import java.util.Observer;

public class vmMenuView extends vmView implements Observer, IVM {

    StringProperty nickname;
    Model m;

    public vmMenuView() {
        nickname = new SimpleStringProperty("Omer");
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o == m) {
            nickname.set(m.getNickname());
        }
    }
}
