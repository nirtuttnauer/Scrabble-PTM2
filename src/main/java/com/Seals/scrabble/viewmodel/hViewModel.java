package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.model.hModel;
import javafx.beans.property.SimpleStringProperty;

public class hViewModel extends ViewModel {
    public hViewModel(iViewModel vm) {
        super(vm.getModelFacade());
        toggleModel();
    }

    public hViewModel(ViewModel vm) {
        super(vm.getModelFacade());
        toggleModel();
        if (vm != null) {
            this.nickname = new SimpleStringProperty(vm.getNickname());
        }
    }

    @Override
    public void setNickname(String nickname) {
        getModelFacade().setNickname(nickname);
    }

    public void startServer() {
        ((hModel) getModelFacade()).startServer();
    }

    public void closeServer() {
        ((hModel) getModelFacade()).stopServer();
    }

    @Override
    public void testDMServerConnection() {
        if (getModelFacade() instanceof hModel) {
            ((hModel) getModelFacade()).testDMServerConnection();
        } else {
            toggleModel();
            ((hModel) getModelFacade()).testDMServerConnection();
            toggleModel(); // Toggle back to the original model
        }
    }
}
