package com.example.scrabble;

import com.example.scrabble.model.Model;
import com.example.scrabble.vm.VM;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookScrabbleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-view.fxml"));
            Parent root = loader.load();

            VM viewModel = new VM(new Model());

            BookScrabbleController controller = loader.getController();
            controller.setVm(viewModel);


            Scene scene = new Scene(root, 300, 200);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setTitle("BookScrabble");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
