package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.model.hModel;
import javafx.beans.property.SimpleStringProperty;

public class hViewModel extends ViewModel {
    public hViewModel(iViewModel vm) {
        super();
        toggleModel();
    }

    public hViewModel(ViewModel vm) {
        super();
        toggleModel();
        if (vm != null) {
            this.vm_nickname = new SimpleStringProperty(vm.getNickname());
        }
    }

    @Override
    public void setNickname(String nickname) {
        model.setNickname(nickname);
    }

    public void startServer() {
        ((hModel) model).startServer();
    }

    public void closeServer() {
        ((hModel) model).stopServer();
    }

    public void testDMServerConnection() {
        if (model instanceof hModel) {
            ((hModel) model).testDMServerConnection();
        } else {
            toggleModel();
            ((hModel) model).testDMServerConnection();
            toggleModel(); // Toggle back to the original model
        }
    }
}