package com.Seals.scrabble.facade;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;

public class ModelFacade implements iModelFacade {
    public hModel getHostModel() {
        return hostModel;
    }

    public Model getModel() {
        return model;
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }

    private hModel hostModel = null;
    private Model model;
    private StringProperty nickname;



    public ModelFacade() {
        this.model = new Model();
        this.nickname = new SimpleStringProperty();
        model.addObserver(this);
    }

    @Override
    public void setNickname(String nickname) {
        if (hostModel != null) hostModel.setNickname(nickname);
        else model.setNickname(nickname);
    }

    @Override
    public String getNickname() {
        return model.getNickname();
    }

    @Override
    public void addObserver(ViewModel viewModel) {
        hostModel.addObserver(viewModel);
        model.addObserver(viewModel);
    }

    @Override
    public void startGame() {
        hostModel.startGame();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == model) {
            nickname.set(model.getNickname());
        }
    }


    // Other methods specific to the HostModel can be added here

    public void hostGame(String serverAddress, int port) {
        if (hostModel == null)
            toggleModels();
        hostModel.startServer();
//         hostModel.connectToServer(serverAddress,port);
    }

    public void joinGame(String serverAddress, int serverPort) {
        if (model == null)
            toggleModels();
        model.connectToHost();
//         model.joinToServer(serverAddress, serverPort);
        // Perform any additional actions or updates related to joining the server as a guest
    }

    public void toggleModels() {
        System.out.println(hostModel + " " + model);
        if (model == null) {
            model = new Model(hostModel);
            hostModel = null;
        } else {
            hostModel = new hModel(model);
            model = null;
        }
    }

    public void TPRequestFromVM(String req){
        String[] cmd = req.split("-");
//        hModel.getGameManager().tryPlaceWordAction();
    }

}