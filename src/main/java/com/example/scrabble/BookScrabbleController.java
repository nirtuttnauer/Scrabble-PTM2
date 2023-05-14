//package com.example.scrabble;
//
//import com.example.scrabble.model.StartModel;
//import com.example.scrabble.model.iModel;
//import com.example.scrabble.vm.StartVM;
//import com.example.scrabble.vm.iVM;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import com.example.scrabble.model.HostModel;
//import com.example.scrabble.vm.HostVM;
//
//public class BookScrabbleController {
//    @FXML
//    private Label MainTitle;
//
//    private iVM viewModel;
//
//    public void initialize() {
//        iModel model = new StartModel();
//        viewModel = new StartVM(model);
//        MainTitle.textProperty().bind(viewModel.welcomeMessageProperty());
//    }
//    @FXML
//    protected void onJoinButtonClick() {
//        viewModel.onJoinButtonClick();
//    }
//    @FXML
//    public void onSettingButtonClick() {
//        viewModel.onSettingButtonClick();
//    }
//    @FXML
//    public void onHostButtonClick() {
//        iModel model = new HostModel();
//        viewModel = new HostVM((HostModel) model);
//        MainTitle.textProperty().bind(viewModel.welcomeMessageProperty());
//    }
//}
