package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.boardAviv.BoardClass;
import com.Seals.scrabble.boardAviv.GameController;
import com.Seals.scrabble.facade.ModelFacade;
import com.Seals.scrabble.model.serverSide.manager.DictionaryManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer, iViewModel {
    private static iViewModel sharedInstance;
    private ModelFacade modelFacade;
    private StringProperty nickname;
    private StringProperty handToView;
    private String newHand;
    private String handFromModel;
    private final int[] values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private BoardClass board;
    private IntegerProperty cordX;
    private IntegerProperty cordY;

    @Override
    public StringProperty getHandProperty() {
        return handToView;
    }

    public ViewModel() {
        handFromModel = new String();
        newHand = new String();
        handToView = new SimpleStringProperty();
        sharedInstance = this;
        modelFacade = new ModelFacade();
        this.nickname = new SimpleStringProperty();
        board = new BoardClass(0, 0); // Create an instance of BoardClass
        cordX = new SimpleIntegerProperty();
        cordY = new SimpleIntegerProperty();
        cordX.bind(GameController.cordXProperty());
        cordY.bind(GameController.cordYProperty());
        handToView.set("A,1,B,2,C,3");
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

    @Override
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

//    public void check(){
//        handFromModel = "UA,AQBRTC";
//        setLetterValue(handFromModel);
//        System.out.println(handToView.get());
//    }
}
