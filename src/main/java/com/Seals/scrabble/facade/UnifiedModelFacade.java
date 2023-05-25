package com.Seals.scrabble.facade;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;
import com.Seals.scrabble.viewmodel.UnifiedViewModel;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;

public class UnifiedModelFacade implements iModelFacade  {
    private hModel hostModel;

    private Model model;
    private StringProperty nickname;


    public UnifiedModelFacade( Model gmodel) {
        this.hostModel = null;
        this.model = gmodel;
        this.nickname = new SimpleStringProperty();
        model.addObserver(this);
    }

    @Override
    public void setNickname(String nickname) {
        hostModel.setNickname(nickname);
        model.setNickname(nickname);
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
    public void update(Observable o, Object arg) {
        if (o == model) {
            nickname.set(model.getNickname());
        }
    }

    public void testDMServerConnection() {
        hostModel.testDMServerConnection();
    }

    // Other methods specific to the HostModel can be added here

    public void connectToServer(String serverAddress , int port){
        if(hostModel==null)
            toggleModels();
        hostModel.connectToServer(serverAddress,port);
    }

    public void joinServerAsGuest(String serverAddress, int serverPort) {
        if(model==null)
            toggleModels();
        model.joinToServer(serverAddress, serverPort);
        // Perform any additional actions or updates related to joining the server as a guest
    }

    public void toggleModels(){
        if(model==null){
            hostModel= new hModel(model);
            model=null;
        }
        else {
            model= new Model(hostModel);
            hostModel=null;
        }
        }
    }

