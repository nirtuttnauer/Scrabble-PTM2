package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.facade.ModelFacade;
import com.Seals.scrabble.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;
import java.util.Observer;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public class ViewModel extends Observable implements Observer, iViewModel {
    Thread tread;
    private static iViewModel sharedInstance;
    private  static ModelFacade modelFacade;
    private StringProperty nickname;
    private StringProperty handToView;
    private String newHand;
    private String handFromModel;
    private final int[] values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private StringProperty id;
    private StringProperty bagAmount;
    private String bagFromModel;
    //private StringProperty tryPlaceWord;



    @Override
    public StringProperty getHandProperty() {
        return handToView;
    }

    public ViewModel() {
 //       tryPlaceWord= new SimpleStringProperty();
        tread = new Thread(this::startGame);
        handFromModel = new String();
        newHand = new String();
        handToView = new SimpleStringProperty();
        bagFromModel = new String();
        sharedInstance = this;
        modelFacade = new ModelFacade();
        this.nickname = new SimpleStringProperty();
        this.id = new SimpleStringProperty();
        this.bagAmount = new SimpleStringProperty();
        id.set("1");
        check();
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
    public StringProperty bagAmountProperty() {
        return bagAmount;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Model) {
            if (arg instanceof String) {
                String check = arg.toString();
                // build the string like this --> UA, 7tiles, id, bag amount, board[][]
                if(check.charAt(0) == 'U' && check.charAt(1) == 'A' && check.charAt(2) == ','){
                    handFromModel = arg.toString();
                    handFromModel = handFromModel.substring(3,9);
                    id.set(arg.toString().substring(11,11));
                    setLetterValue(handFromModel);
                    bagFromModel = arg.toString();
                    bagAmount.set(bagFromModel.substring(13,14));
                }
            }
         }
            nickname.set(modelFacade.getNickname());
    }

    // Additional methods and functionality specific to ViewModel

    public void hostGame() {
        modelFacade.hostGame(Settings.getServerAddress(), Settings.getHostServerPort());
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

    public void check(){
        handFromModel = "UA,AQBRTC";
        bagFromModel = "BA,30";
        bagAmount.set(bagFromModel.substring(3));
        setLetterValue(handFromModel);
        System.out.println(handToView.get());
        System.out.println(bagAmountProperty().get());
    }
    public static void startServer(){
        modelFacade.toggleModels();
        modelFacade.getHostModel().startServer();
    }

    @Override
    public void joinGame(){
        modelFacade.joinGame("omer", 5);
    }

    @Override
    public void startGame() {
        modelFacade.startGame();
    }

    public void setNewHand(String string){
        String newHandView = new String(string);
        setChanged();
        notifyObservers();
    }

    @Override
    public StringProperty getIdProperty() {
        return id;
    }

    @Override
    public void updateTryPlaceWordInViewModel(String val){
        StringBuilder sb = new StringBuilder("TP-");
        System.out.println("updateTryPlaceWordInViewModel was call form viewModel");
        sb.append(val);
        modelFacade.TPRequestFromVM(sb.toString());
        // nir need to get the boardClass instance , and update all the threads for the changes
    }

    @Override
    public void changeScheneToGame() {
        setScene("GameView");
        tread.start();
    }

}
