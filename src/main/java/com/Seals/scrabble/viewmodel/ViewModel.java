package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.controller.LoginController;
import com.Seals.scrabble.facade.ModelFacade;
import com.Seals.scrabble.model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public class ViewModel extends Observable implements Observer, iViewModel {
    Thread tread;
    private static iViewModel sharedInstance;
    private static ModelFacade modelFacade;
    private StringProperty nickname;
    private StringProperty handToView;
    private String newHand;
    private String handFromModel;
    private final int[] values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private StringProperty id;
    private StringProperty bagAmount;

    public StringProperty getBagFromModel() {
        return bagFromModel;
    }

    public StringProperty bagFromModelProperty() {
        return bagFromModel;
    }

    private StringProperty bagFromModel;
    public StringProperty board;
    //private StringProperty tryPlaceWord;


    @Override
    public StringProperty getHandProperty() {
        return handToView;
    }

    public ViewModel() {
        //       tryPlaceWord= new SimpleStringProperty();
        tread = new Thread(this::startGame);
        board = new SimpleStringProperty();
        handFromModel = new String();
        newHand = new String();
        handToView = new SimpleStringProperty();
        bagFromModel = new SimpleStringProperty();
        sharedInstance = this;
        modelFacade = new ModelFacade();
        this.nickname = new SimpleStringProperty();
        this.id = new SimpleStringProperty();
        this.bagAmount = new SimpleStringProperty();
        id.set("" + 0);
        //check();
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
//        this.nickname.set(nickname);
//        modelFacade.setNickname(nickname);
//        setChanged();
//        notifyObservers();
    }

    public void setNickName() {
        nickname.bind(LoginController.getNickNameProperty());
    }

    @Override
    public String getNickname() {
        return nickname.get();
    }

    @Override
    public StringProperty nicknameProperty() {
        return nickname;
    }

    public StringProperty bagAmountProperty() {
        return bagAmount;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Model) {
            if (arg instanceof String) {
                System.err.println("args: " + arg);
//                id.set(String.valueOf(modelFacade.getID()));
                String check = arg.toString();
                String boardFromModel = (arg.toString());
                String[] stringArr = boardFromModel.split(",");

                // build the string like this --> UA, 7tiles, id, bag amount,

                if (stringArr[0].equals("UA")) {
                    System.err.println(Integer.getInteger(this.id.get()) == Integer.getInteger(stringArr[2].toString()));
                 if (this.id.get().equals(stringArr[2])){

                        if (check.length() >= 10) {
                            handFromModel = "";
                            handFromModel = check.substring(3, 10);
                        } else {
                            handFromModel = "";
                            System.out.println("Received shorter string than expected. Check the server response.");
                        }

                        if (check.length() >= 15) {
                            bagAmount.set(check.substring(13, 15));
                        } else {
                            bagAmount.set("");
                            System.out.println("Received shorter string than expected. Check the server response.");
                        }

                        setLetterValue();
                    } else {
                        System.out.println("failed " + check);
                    }
                } else if (stringArr[0].equals("board")) {
                    board.set(stringArr[1]);
                    System.err.println(board.get());
                } else if (stringArr[0].equals("ID")) {
                    if (id.get().equals(String.valueOf(0))) {
                        System.err.println(stringArr.toString());
                        this.id.set(stringArr[1]);
                        System.err.println(id.get());
                    }

                }

                System.out.println("VM line 115: " + handToView);
                System.out.println("VM line 116: " + board);
                System.out.println("VM line 117: " + id.get());
            }
        }
        //nickname.set(modelFacade.getNickname());
    }


    // Additional methods and functionality specific to ViewModel

    public void hostGame() {
        modelFacade.hostGame(Settings.getServerAddress(), Settings.getHostServerPort());
    }

    public void setLetterValue() {
        Platform.runLater(() -> {
            newHand = "";
            for (int i = 0; i < handFromModel.length(); i++) {
                System.out.println("line 120: " + handFromModel);
                int value = values[(int) (handFromModel.charAt(i) - 'A')];
                newHand += handFromModel.charAt(i) + "," + value + ",";
            }
            newHand = newHand.substring(0, newHand.length() - 1);
            setHandToView();
        });
    }

    public void setHandToView() {
        Platform.runLater(() -> {
            handToView.set(newHand);
        });
    }

    public void check() {
        handFromModel = "AAAAAAA";
        bagFromModel.set( "BA,30");
        String bag = bagFromModel.get();
        bagAmount.set(bag.substring(3));
        setLetterValue();
        System.out.println(handToView.get());
        System.out.println(bagAmountProperty().get());
    }

    public static void startServer() {
        modelFacade.toggleModels();
        modelFacade.getHostModel().startServer();
    }

    @Override
    public void joinGame() {
        modelFacade.joinGame("omer", 5);
    }

    @Override
    public void startGame() {
        modelFacade.startGame();
    }

    public void setNewHand(String string) {
        String newHandView = new String(string);
        setChanged();
        notifyObservers();
    }

    @Override
    public StringProperty getIdProperty() {
        return id;
    }

    @Override
    public void updateTryPlaceWordInViewModel(String val) {
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


    public StringProperty getBoardProperty() {
        return board;
    }


}
