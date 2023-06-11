package com.Seals.scrabble.boardAviv;

import com.Seals.scrabble.controller.iController;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

import java.io.IOException;
import java.util.*;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public class GameController implements Observer, iController {


    @FXML
    VBox VboxBoard;

    @FXML
    HBox HandHbox;
    private BoardClass boardClass;
    private Affine affine;

    private StringProperty handString;

    private Hand hand;

    private List<Pane> paneList;
    private String letterFromHand;

    @FXML
    public void initialize() {
        this.paneList= new ArrayList<>();
        this.letterFromHand = "";
        this.handString = new SimpleStringProperty();

        handString.bind(ViewModel.getHand());
        if (handString.get() != null) {
            System.out.println(handString.get());
            hand = new Hand(handString.get());
        } else {
            System.out.println("Hand object didn't create, handString is null");
        }

        this.affine = new Affine();
        this.affine.appendScale(600.0 / 15, 600.0 / 15);

        this.boardClass = new BoardClass(15, 15);
        draw();


        if (hand != null) {
            drawHand();
        }

    }





    public void drawHand() {
        HandHbox.getChildren().clear();

        BackgroundFill backgroundFill = new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        for (int i = 0; i < hand.tiles.length; i++) {
            Pane handPane = new Pane();
            handPane.setBackground(background);
            Label score = new Label();
            Label letter = new Label();

            letter.setText(hand.tiles[i]);
            score.setText("0");

            Insets labelsPadding = new Insets(47);
            score.setPadding(labelsPadding);
            letter.setPadding(new Insets(10));


            handPane.getChildren().addAll(score, letter);

            // add the Tile to the paneList
            paneList.add(handPane);

            HandHbox.getChildren().add(handPane);

            handPane.setOnMouseClicked(event -> {
                handPane.setBackground(Background.fill(Color.BLUE));
                // Handle the click event on the pane
                System.out.println("Clicked on pane: " + handPane.getId());
                letterFromHand = letter.getText();
                System.out.println("The letter from the label: " + letterFromHand);
                // Perform further actions with the letter
            });

            handPane.setMinHeight(100.0);

        }
    }




    private void draw() {
        VboxBoard.getChildren().clear();
        VboxBoard.setAlignment(Pos.CENTER);
        for (int i = 0; i < 15; i++) {
            HBox row = new HBox();
            for (int j = 0; j < 15; j++) {
                Pane pane = new Pane();
                pane.setMinSize(40, 40);
                pane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");// Set the background color of the pane

                pane.setOnMouseClicked(event -> {
                    if(!letterFromHand.equals("")){
                        Label letter = new Label();
                        letter.setText(letterFromHand);
                        pane.getChildren().add(letter);
                    }
                    letterFromHand="";
                    drawHand();
                });
                row.getChildren().add(pane);
            }
            VboxBoard.getChildren().add(row);
        }
    }



    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ViewModel) {
            System.out.println("Update from " + this.getClass().toString());
        }
    }

    @Override
    public void onMenuButtonClick() throws IOException {
        setScene("MenuView");
    }

    @Override
    public void onExitButtonClick() throws IOException {
        Platform.exit();
    }

    public void generateTile(ActionEvent actionEvent) {
        if(paneList.size()>=7){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception!");
            alert.setHeaderText("Wrong step!");
            alert.setContentText("you already have 7 tiles");

            // Display the alert dialog
            alert.showAndWait();
        }
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Create a Random object
        Random random = new Random();

        // Generate a random index within the range of the letters string length
        int randomIndex = random.nextInt(letters.length());

        // Get the random letter at the generated index
        char randomLetter = letters.charAt(randomIndex);
        Pane p = new Pane();
        Label letter = new Label();
        letter.setText(String.valueOf(randomLetter));
        Label score = new Label();
        p.getChildren().addAll(score,letter);
        paneList.add(p);
        drawHand();
    }
}

