package com.example.scrabble.vm.generics;

import com.example.scrabble.model.GuestModel;
import com.example.scrabble.model.ModelData;
import com.example.scrabble.model.ScrabbleFacade;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GuestVM extends VM {
    private final ScrabbleFacade model;
    private final StringProperty welcomeMessage;

    public GuestVM(GuestModel model) {

        this.model = new ScrabbleFacade();
        welcomeMessage = new SimpleStringProperty();

    }

    @Override
    public void setNickname(String String) {
        model.setNickname(String);
    }
}
