package com.example.scrabble.vm;

import com.example.scrabble.model.GuestModel;

import java.util.Observable;
import java.util.Observer;

public class GuestVM implements Observer {
    private final GuestModel model;
    //private final View view;

    public GuestVM(GuestModel model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass()==model.getClass()){}
    }
}
   // public StringProperty welcomeMessageProperty() {
//        return welcomeMessage;
//    }
//
//    public void onHelloButtonClick() {
//        model.setWelcomeMessage("Lama Omer Kaha Kaved?!");
//        welcomeMessage.set(model.getWelcomeMessage());
//    }
//
//    public void onJoinButtonClick() {
//        model.setWelcomeMessage("Lama");
//        welcomeMessage.set(model.getWelcomeMessage());
//    }
