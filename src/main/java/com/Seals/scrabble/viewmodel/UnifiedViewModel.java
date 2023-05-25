package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;
import com.Seals.scrabble.model.iModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;
import java.util.Observer;

public class UnifiedViewModel extends Observable implements Observer, iViewModel {
    private static iViewModel sharedInstance;
    private iModel model;
    private StringProperty nickname;

    public UnifiedViewModel() {
        sharedInstance = this;
        this.model = new hModel((iModel) this);
        this.nickname = new SimpleStringProperty();
    }

    public static iViewModel getSharedInstance() {
        return sharedInstance;
    }

    public static void setSharedInstance(iViewModel instance) {
        System.out.println("Setting shared instance");
        System.out.println(instance);
        sharedInstance = instance;
    }

    public UnifiedViewModel(hViewModel hostVM) {
        sharedInstance = this;
        this.model = new hModel((iModel) this);
        this.nickname = new SimpleStringProperty(hostVM.getNickname());
    }

    public void toggleModel() {
        if (model instanceof hModel) {
            this.model = new Model();
        } else {
            this.model = new hModel((iModel) this);
        }
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname.set(nickname);
        model.setNickname(nickname);
        setChanged();
        notifyObservers();
    }

    @Override
    public String getNickname() {
        return nickname.get();
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == model) {
            nickname.set(model.getNickname());
        }
    }

    // Additional methods and functionality specific to ViewModel

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
