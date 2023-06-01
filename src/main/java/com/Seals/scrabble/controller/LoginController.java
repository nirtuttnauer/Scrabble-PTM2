package com.Seals.scrabble.controller;

import com.Seals.scrabble.factories.SceneFactory;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class LoginController implements iController, Observer {
    ViewModel viewModel;
    @FXML
    TextField nameField;
    private static StringProperty nickNameProperty = new SimpleStringProperty();
    @FXML
    Button submitButton;



    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addObserver(this);
        System.out.println(" This Observer added from" + this.getClass().toString() + "to " + viewModel.getClass().toString());
    }

    @FXML
    private void initialize() {
        // Initialize the controller

//        submitButton.setOnAction(event -> {
//            String name = nameField.getText();
//            viewModel.setNickname(name);

//            try {
//                onMenuButtonClick();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        //});
    }

    @Override
    public void onMenuButtonClick() throws IOException {
        // Implement your logic to switch to the menu view
        SceneFactory.setScene("MenuView");
    }

    @Override
    public void onExitButtonClick() throws IOException {
        System.exit(0);
    }

    public void onSubmitButtonClick(ActionEvent event) throws Exception {
        System.out.println("Submit button clicked");
        MenuController.getNickName().bind(nickNameProperty);
        nickNameProperty.set(nameField.getText());
        SceneFactory.setScene("MenuView");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ViewModel) {
            System.out.println("Update from" + this.getClass().toString());
        }
    }

    public static StringProperty getNickNameProperty(){
        return nickNameProperty;
    }
    public static String getNickNameP() {
        return nickNameProperty.get();
    }



}