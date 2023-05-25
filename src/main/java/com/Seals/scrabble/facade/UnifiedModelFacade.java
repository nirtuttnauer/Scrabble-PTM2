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

    private UnifiedViewModel vm;

    public UnifiedModelFacade(UnifiedViewModel viewModel , Model gmodel, hModel hModel) {
        this.hostModel = hModel;
        this.model = gmodel;
        this.nickname = new SimpleStringProperty();
        model.addObserver(this);
        this.vm=viewModel;
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
        hostModel.connectToServer(serverAddress,port);
    }

    public void joinServerAsGuest(String serverAddress, int serverPort) {
        // Connect to the server as a guest
         model.joinToServer(serverAddress, serverPort);
        // Perform any additional actions or updates related to joining the server as a guest
    }

}
