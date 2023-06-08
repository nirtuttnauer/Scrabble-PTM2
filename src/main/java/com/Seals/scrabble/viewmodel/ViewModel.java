package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.facade.ModelFacade;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer, iViewModel {
    private static iViewModel sharedInstance;
    private StringProperty nickname;

    private final ModelFacade modelFacade;

     private static StringProperty hand;

    public static StringProperty getHand() {
        return hand;
    }

    public StringProperty handProperty() {
        return hand;
    }

    public ViewModel() {
        this.hand= new SimpleStringProperty();
        hand.set("ABBBCAS");
        sharedInstance = this;
        modelFacade = new ModelFacade();
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

    @Override
    public void setNickname(String nickname) {
        this.nickname.set(nickname);
        modelFacade.setNickname(nickname);
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
//         if (o == modelFacade) {
//             nickname.set(model.getNickname());
    }
    // Additional methods and functionality specific to ViewModel

//     public void startServer() {
//         (modelFacade.startServer();
//     }

//     public void closeServer() {
//         ((hModel) model).stopServer();
//     }

    public void hostGame() {
        modelFacade.hostGame(Settings.getServerAddress(), Settings.getHostServerPort());
    }

    public void joinGame() {
        modelFacade.joinGame(Settings.getServerAddress(), Settings.getHostServerPort());
    }

}