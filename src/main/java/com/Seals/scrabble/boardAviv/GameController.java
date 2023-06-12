package com.Seals.scrabble.boardAviv;

import com.Seals.scrabble.controller.iController;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public class GameController implements Observer, iController {

    private IntegerProperty cordX;
    private IntegerProperty cordY;
    @FXML
    Button confirmChangesBTN;
    @FXML
    private Label player4;
    @FXML
    private Label player3;
    @FXML
    private Label player2;
    @FXML
    private Label player1;
    @FXML
    private VBox VboxBoard;

    @FXML
    private HBox HandHbox;
    private BoardClass boardClass;
    private Affine affine;

    private StringProperty handString;


    private String letterFromHand;

    @FXML
    public void initialize() {

        // set changes button
        this.confirmChangesBTN.setVisible(false);
        confirmChangesBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Are you sure about this changes?");
                ButtonType yes = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");
                alert.getButtonTypes().addAll(yes,no);

                alert.setOnCloseRequest(event->{
                    ButtonType res = alert.getResult();
                    if(res == yes){
                        alert.setContentText("Checking your new data...");
                    }
                    else if (res==no){
                        alert.setContentText("No problem, try again!");
                    }
                });
                alert.showAndWait();
            }
        });

        // StringProperty...
        this.letterFromHand = "";
        this.handString = new SimpleStringProperty();
        handString.bind(ViewModel.getSharedInstance().getHand());
        if (handString.get() != null) {
            drawHand();
        } else {
            System.out.println("Hand object didn't create, handString is null");
        }
        ViewModel.getSharedInstance().getHand().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                System.out.println("addListener() called , the new value is : "+ newValue);
                drawHand();
            }
        });

        //affine
        this.affine = new Affine();
        this.affine.appendScale(600.0 / 15, 600.0 / 15);

        // boardClass
        this.boardClass = new BoardClass(15, 15);

        //draw the board
        draw();

    }





    public void drawHand() {
        HandHbox.getChildren().clear();

        BackgroundFill backgroundFill = new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        for (int i = 0; i < handString.get().length(); i++) {
            Pane handPane = new Pane();
            handPane.setBackground(background);
            Label score = new Label();
            Label letter = new Label();

            letter.setText(String.valueOf(handString.get().charAt(i)));
            score.setText("0");

            Insets labelsPadding = new Insets(47);
            score.setPadding(labelsPadding);
            letter.setPadding(new Insets(10));


            handPane.getChildren().addAll(score, letter);


            HandHbox.getChildren().add(handPane);

            handPane.setOnMouseClicked(event -> {
                if (handPane.getBackground().equals(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)))) {
                    letterFromHand="";
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setContentText("You already used this tile!\nTry a different tile.");
                    alert.showAndWait();
                } else {
                    handPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    // Handle the click event on the pane
                    System.out.println("Clicked on pane: " + handPane.getId());
                    letterFromHand = letter.getText();
                    System.out.println("The letter from the label: " + letterFromHand);
                    // Perform further actions with the letter
                }
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

                setColor(pane,i,j);
                int finalI = i;
                int finalJ = j;
                pane.setOnMouseClicked(event -> {
                    int x = finalI;
                    int y= finalJ;
                    if(!letterFromHand.equals("")){
                        Label letter = new Label();
                        letter.setText(letterFromHand);
                        boardClass.setBoard(x,y,letterFromHand);
                        boardClass.printBoard();
                        pane.getChildren().add(letter);
                        confirmChangesBTN.setVisible(true);
                    }
                    letterFromHand="";
                });
                row.getChildren().add(pane);
            }
            VboxBoard.getChildren().add(row);
        }
    }

    private void setColor(Pane pane, int i, int j) {
        // red part
        if((i==0 && j==0) ||(i==0 && j==7) ||(i==0 && j==14) || (i==7 && j==14) ||(i==7 && j==14) || (i==14 && j==14)|| (i==14 && j==7)|| (i==14 && j==0)|| (i==7 && j==0) )
            pane.setStyle("-fx-background-color: red");

        // light blue part
      else if((i==0 && j==3) || (i==0 && j==11) || (i==2 && j==6) || (i==2 && j==8) || (i==3 && j==7) || (i==3 && j==0) || (i==3 && j==14) ||
                (i==6 && j==2) || (i==6 && j==6) || (i==6 && j==8) || (i==7 && j==3) || (i==7 && j==11) || (i==8 && j==2) || (i==8 && j==6)
                || (i==8 && j==8) || (i==8 && j==12) || (i==11 && j==0) || (i==11 && j==7) || (i==11 && j==14) || (i==12 && j==6) ||
                (i==12 && j==8) || (i==14 && j==3) ||(i==14 && j==11))
                        pane.setStyle("-fx-background-color:  #00ffea;-fx-border-color: black; -fx-border-width: 1px;");

        // The blue part
        else if((i==1 && j==5) || (i==1 && j==9) ||(i==5 && j==1) ||(i==5 && j==5) ||(i==5 && j==9) || (i==5 && j==13) ||
                (i==9 && j==1) ||(i==9 && j==5) ||(i==9 && j==9) ||(i==9 && j==13) || (i==13 && j==5) || (i==13 && j==9))
                pane.setStyle("-fx-background-color: blue;-fx-border-color: black; -fx-border-width: 1px;");

        // yellow part
        else if((i==1 && j==1) ||(i==2 && j==2) ||(i==3 && j==3) || (i==4 && j==4) || (i==1 && j==13) || (i==2 && j==12) ||
                (i==3 && j==11) || (i==4 && j==10) || (i==10 && j==4) ||(i==11 && j==3) || (i==12 && j==2) ||
                (i==13 && j==1) || (i==10 && j==10) || (i==11 && j==11) || (i==12 && j==12) ||(i==13 && j==13))
            pane.setStyle("-fx-background-color: #e3e362;-fx-border-color: black; -fx-border-width: 1px;");

        // middle part
        else if((i==7 && j==7)) {
            pane.setStyle("-fx-background-color: #e3e362;-fx-border-color: black; -fx-border-width: 1px;");
            Label label= new Label();
            label.setText("Star");
            pane.getChildren().add(label);
        }
        else
            pane.setStyle("-fx-background-color: grey;-fx-border-color: black; -fx-border-width: 1px;");
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


}

