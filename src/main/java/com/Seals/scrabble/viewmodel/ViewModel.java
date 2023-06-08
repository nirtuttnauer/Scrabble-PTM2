package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.facade.ModelFacade;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer, iViewModel {
    private static iViewModel sharedInstance;
    private StringProperty nickname;

    private final ModelFacade modelFacade;


    private static StringProperty handToView;
     private String newHand;
     private String handFromModel;
    private final int[] values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    public static StringProperty getHandToView() {
        return handToView;
    }

    public StringProperty handProperty() {
        return handToView;
    }

    public ViewModel() {
        handFromModel = new String();
        newHand = new String();
        handToView = new SimpleStringProperty();
        sharedInstance = this;
        modelFacade = new ModelFacade();
        this.nickname = new SimpleStringProperty();
//        check();
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
//        if (o instanceof ModelFacade) {
//            if (arg instanceof String) {
//                String check = arg.toString();
//                if(check.charAt(0) == 'U' && check.charAt(1) == 'A' && check.charAt(2) == ',')
//                    handFromModel = arg.toString();
//                    setLetterValue(handFromModel);
//            }
//         }
//            nickname.set(model.getNickname());
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
    public void setLetterValue(String tiles){
        for (int i = 3; i < handFromModel.length(); i++){
            int value = values[tiles.charAt(i) - 'A'];
            newHand += handFromModel.charAt(i) + "," + value +",";
        }
        newHand = newHand.substring(0, newHand.length()-1);
        setHandToView();
    }

    public void setHandToView(){
        handToView.set(newHand);
    }

    public static StringProperty handToViewProperty() {
        return handToView;
    }

    public static void setHandToView(String handToView) {
        ViewModel.handToView.set(handToView);
    }

//    public void check(){
//        handFromModel = "UA,AQBRTC";
//        setLetterValue(handFromModel);
//        System.out.println(handToView.get());
//    }
}
