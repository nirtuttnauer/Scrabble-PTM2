package com.Seals.scrabble.facade;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;

public class ModelFacade implements iModelFacade {
    private Model model;
    private StringProperty nickname;

    public ModelFacade() {
        this.model = new Model();
        this.nickname = new SimpleStringProperty();
        model.addObserver(this);
    }

    @Override
    public void setNickname(String nickname) {
        model.setNickname(nickname);
    }

    @Override
    public String getNickname() {
        return model.getNickname();
    }

    @Override
    public void addObserver(ViewModel viewModel) {
        model.addObserver(viewModel);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == model) {
            nickname.set(model.getNickname());
        }
    }
}