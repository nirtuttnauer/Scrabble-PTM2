package com.Seals.scrabble.vm;

import com.Seals.scrabble.model.HostModel;
import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.iModel;
import javafx.beans.property.SimpleStringProperty;

import java.util.Observable;
import java.util.Observer;

public class VM extends Observable implements Observer, IVM {
    public iModel model;
    public SimpleStringProperty vm_nickname;

    public VM() {
        this.model = new Model();
        vm_nickname = new SimpleStringProperty();
    }

    public VM(HostVM hostVM) {
        toggleModel();
        this.setNickname(hostVM.getNickname());
    }

    public void toggleModel() {
        if (!(model instanceof HostModel)) {
            this.model = new HostModel((Model) model);
        } else {
            this.model = new Model((HostModel) model);
        }
    }

    public void setNickname(String nickname) {
        this.vm_nickname.set(nickname);
        model.setNickname(nickname);
    }

    public String getNickname() {
        return model.getNickname();
    }

    public SimpleStringProperty nicknameProperty() {
        return vm_nickname;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == model) {
            vm_nickname.set(model.getNickname());
        }
    }

    // Additional methods and functionality specific to VM

    public void testDMServerConnection() {
        System.out.println("Testing DM server connection");

        // Ensure the appropriate casting is performed
        if (model instanceof HostModel) {
            HostModel hostModel = (HostModel) model;
            System.out.println(this.getNickname());
            String response = hostModel.sendRequestToServer(this.getNickname());
            System.out.println("Received response: " + response);
        } else {
            System.out.println("The model is not an instance of HostModel");
        }
    }
}
