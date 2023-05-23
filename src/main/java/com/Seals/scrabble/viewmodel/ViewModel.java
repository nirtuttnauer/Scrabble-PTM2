package com.Seals.scrabble.viewmodel;

import com.Seals.scrabble.Settings;
import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;
import com.Seals.scrabble.model.iModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer, iViewModel {
    private static iViewModel sharedInstance;
    protected iModel model;
    StringProperty vm_nickname;

    public ViewModel() {
        sharedInstance = this;
        this.model = new Model();
        vm_nickname = new SimpleStringProperty();
    }

    public static iViewModel getSharedInstance() {
        return sharedInstance;
    }

    public static void setSharedInstance(iViewModel instance) {
        System.out.println("Setting shared instance");
        System.out.println(instance);
        sharedInstance = instance;
    }

    public ViewModel(hViewModel hostVM) {
        sharedInstance = this;
        toggleModel();
        this.setNickname(hostVM.getNickname());
    }

    public void toggleModel() {
        if (!(model instanceof hModel)) {
            this.model = new hModel((Model) model);
        } else {
            this.model = new Model((hModel) model);
        }
    }

    public void setNickname(String nickname) {
        this.vm_nickname.set(nickname);
        model.setNickname(nickname);
        setChanged();
        notifyObservers();
    }

    public String getNickname() {
        return model.getNickname();
    }

    public StringProperty nicknameProperty() {
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
        if (model instanceof hModel) {
            hModel hModel = (hModel) model;
            String response = hModel.sendRequestToServer(this.getNickname());
            System.out.println("Received response: " + response);
        } else {
            System.out.println("The model is not an instance of hModel");
        }
    }

    public StringProperty getVm_nickname() {
        return vm_nickname;
    }

}
