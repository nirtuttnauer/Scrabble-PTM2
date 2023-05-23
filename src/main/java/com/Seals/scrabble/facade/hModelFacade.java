package com.Seals.scrabble.facade;

import com.Seals.scrabble.model.hModel;
import com.Seals.scrabble.model.iModel;
import com.Seals.scrabble.viewmodel.ViewModel;

import java.util.Observable;

public class hModelFacade implements iModelFacade {
    private hModel hostModel;

    public hModelFacade(iModelFacade model) {
        this.hostModel = new hModel((iModel) model);
    }

    @Override
    public void setNickname(String nickname) {
        hostModel.setNickname(nickname);
    }

    @Override
    public String getNickname() {
        return hostModel.getNickname();
    }

    @Override
    public void addObserver(ViewModel viewModel) {
        hostModel.addObserver(viewModel);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void testDMServerConnection() {
        hostModel.testDMServerConnection();
    }

    // Other methods specific to the HostModel
}
