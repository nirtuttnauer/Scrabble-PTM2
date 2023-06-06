package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.boardAviv.GameController;
import com.Seals.scrabble.facade.ModelFacade;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer, iViewModel {
    private static iViewModel sharedInstance;
    private StringProperty nickname;
    private static IntegerProperty value = new SimpleIntegerProperty();
    private static int[] valuesArray = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private static StringProperty letterToView = new SimpleStringProperty();


    private final ModelFacade modelFacade;

    public ViewModel() {
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
//         }
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

    public String getLetterFromModel() {
        return letterToView.get();
    }

    public StringProperty letterFromModelProperty() {
        return letterToView;
    }

    public void setLetterFromModel(String letterFromModel) {
        this.letterToView.set(letterFromModel);
    }
//    public static void bindLetterAndValueToView() throws Exception{
//        GameController.getLetter.bind(letterToView); // getLetter should return StringProperty;
//        GameController.getLetterValue.bind(value); //getLetterValue should return IntegerProperty;
//        int index = (int) (letterToView.get().charAt(0)-'A');
//        value.setValue(valuesArray[index]); //init the value and bind it to View;
//    }

}
