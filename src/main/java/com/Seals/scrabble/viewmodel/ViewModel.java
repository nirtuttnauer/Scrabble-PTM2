package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.boardAviv.GameController;
import com.Seals.scrabble.facade.ModelFacade;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer, iViewModel {
    private static iViewModel sharedInstance;
    private  ModelFacade modelFacade;

    public StringProperty getTryPlaceWord() {
        return tryPlaceWord;
    }

    public StringProperty tryPlaceWordProperty() {
        return tryPlaceWord;
    }

    private StringProperty tryPlaceWord;
    private StringProperty nickname;
    private StringProperty hand;

    public StringProperty getHand() {
        return hand;
    }

    public StringProperty handProperty() {
        return hand;
    }

    public ViewModel() {
        this.tryPlaceWord= new SimpleStringProperty();
        if(bindForTryPlaceWord()) {
            GameController.getTryPlaceWord().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    // send data for the modelFacade

                }
            });
        }
        this.hand = new SimpleStringProperty();
        sharedInstance = this;
        hand.set("ABBBCAS");
        modelFacade = new ModelFacade();
        this.nickname = new SimpleStringProperty();

    }
    public boolean bindForTryPlaceWord(){
        try {
            this.tryPlaceWord.bind(GameController.getTryPlaceWord());
        }catch (Exception e){
            System.out.println("Catch from bindForTryPlaceWord int viewmodel");
            return false;
        }
        return true;
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
        // Handle update logic
    }

    // Additional methods and functionality specific to ViewModel

    public void hostGame() {
        modelFacade.hostGame(Settings.getServerAddress(), Settings.getHostServerPort());
    }

    public void joinGame() {
        modelFacade.joinGame(Settings.getServerAddress(), Settings.getHostServerPort());
    }

}
