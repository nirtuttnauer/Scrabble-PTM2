package com.example.scrabble.vm.generics;
import com.example.scrabble.model.ScrabbleFacade;
import javafx.beans.property.SimpleStringProperty;
import java.util.Observable;
import java.util.Observer;

public class VM extends Observable implements Observer, IVM {
    private final ScrabbleFacade model;
    public SimpleStringProperty vm_nickname;

    public VM() {
        this.model = new ScrabbleFacade();
        vm_nickname = new SimpleStringProperty();
    }

    public void setNickname(String nickname) {
        this.vm_nickname.set(nickname);
        model.setNickname(nickname);
    }

    public String getNickname() {
        return model.getNickname();
    }

    public SimpleStringProperty nicknameProperty() {
        return vm_nickname;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == model) {
            vm_nickname.set(model.getNickname());
        }
    }
}