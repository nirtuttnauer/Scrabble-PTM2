package com.Seals.scrabble.controller;

import com.Seals.scrabble.viewmodel.ViewModel;
import com.Seals.scrabble.viewmodel.iViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import static com.Seals.scrabble.viewmodel.ViewModel.getSharedInstance;
import static com.Seals.scrabble.viewmodel.ViewModel.setSharedInstance;

public class MainController implements Observer {
    public iViewModel vm;

    public iViewModel getVm() {
        return vm;
    }

    public void setVm(iViewModel vm) {
        this.vm = vm;
    }

    @FXML
    protected AnchorPane includedViewContainer; // Injected using @FXML annotation

    public void initialize(URL location, ResourceBundle resources) {
        if (getSharedInstance() == null)
            setSharedInstance(new ViewModel());
        this.vm = getSharedInstance();
        if (this.vm != null)
            this.vm.addObserver(this);
        String currentView = "login-view.fxml"; // Set the desired FXML file dynamically
        loadIncludedView(currentView);
    }

    protected void loadIncludedView(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Seals/scrabble/view/" + fxmlFile));

        try {
            AnchorPane includedView = loader.load();
            includedViewContainer.getChildren().setAll(includedView); // Use the injected field
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onExitButtonClick(ActionEvent event) {
    }

    public void onSwitchButtonClick(ActionEvent event) {
    }

    public void setVM(iViewModel vm) {
        this.vm = vm;
        if (this.vm != null) {
            this.vm.addObserver(this);
        }
    }

    public MainController() {
        this.vm = getSharedInstance();
        if (this.vm != null)
            this.vm.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

