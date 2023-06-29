package com.Seals.scrabble.boardAviv;

import com.Seals.scrabble.controller.MenuController;
import com.Seals.scrabble.controller.iController;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

import java.io.IOException;
import java.util.*;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public class GameController extends StackPane implements Observer, iController {

    @FXML
    VBox rightVBox;
    @FXML
    Button bagBTN;
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
    private Label bagLbl;
    @FXML
    private HBox HandHbox;
    private List<ConfrimTiles> tiles;
    private List<String> paneHandList;
    private volatile boolean isFirstPlay = true;
    private static BoardClass boardClass= new BoardClass(15,15);
    private Affine affine;
    private  StringProperty bag;
    private StringProperty handString;
    private String letterFromHand;
    private List<Pane> paneList;
    private String handChanges;
    private  StringProperty id;
    public StringProperty boardFromOmer;

    @FXML
    public void initialize() {
        //example
        //boardClass.setBoard(0,0,"A");
        //list of tiles that will transform to the vm
        tiles= new ArrayList<>();
        paneList = new ArrayList<>();

        // players label
        id= new SimpleStringProperty();
        id.bind(ViewModel.getSharedInstance().getIdProperty());
        handleId();
        // pane list
        paneHandList = new ArrayList<>();
        // set changes button
        this.confirmChangesBTN.setVisible(false);
        confirmChangesBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Are you sure about these changes?");
                ButtonType yes = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");
                alert.getButtonTypes().addAll(yes, no);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == yes) {
                    alert.setContentText("Checking your new data...");
                    handleTryPlaceWord();
                    removeFromHand();
                } else {
                    alert.setContentText("No problem, try again!");
                    coloriseHandToBasicColor();
                    removeFromBoard();
                }
                confirmChangesBTN.setVisible(false);
            }
        });


        // StringProperty...
        this.boardFromOmer= new SimpleStringProperty();
        this.boardFromOmer.bind(ViewModel.getSharedInstance().bagFromModelProperty());
        this.bag = new SimpleStringProperty();
        this.bag.bind(ViewModel.getSharedInstance().bagAmountProperty());
        this.letterFromHand = "";
        this.handString = new SimpleStringProperty();
        handString.bind(ViewModel.getSharedInstance().getHandProperty());
        if (handString.get() != null) {
            drawHand();
        } else {
            System.out.println("Hand object didn't create, handString is null");
        }

        //add listeners
        ViewModel.getSharedInstance().bagFromModelProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                boardClass.setAll(boardFromOmer.get());
                System.out.println("Changes from omer coming on...");
                draw();
            }
        });
       ViewModel.getSharedInstance().bagAmountProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
               bagLbl.setText(bag.get());
           }
       });

        ViewModel.getSharedInstance().getHandProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                System.out.println("addListener() called , the new value is : "+ newValue);
                drawHand();
            }
        });

        bagBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // practice!
                boardClass.setBoard(0,0,"A");
                boardClass.setBoard(1,1,"V");
                boardClass.setBoard(2,2,"i");
                boardClass.setBoard(3,3,"v");

                ViewModel.getSharedInstance().bagFromModelProperty().set(builtBoard());
              if(paneHandList.size()==0){
                  // alert....
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setContentText("You already got 7 tiles...");
                  alert.showAndWait();
              }
              else {
                  ViewModel.getSharedInstance().setNewHand(handChanges);
              }
            }
        });

        //affine
        this.affine = new Affine();
        this.affine.appendScale(600.0 / 15, 600.0 / 15);

        //draw the board
        draw();

    }

    private String builtBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if(j<14)
                sb.append(boardClass.getBoard()[i][j]).append(",");
                else sb.append(boardClass.getBoard()[i][j]);
            }
            if(i<14)
            sb.append("\n");
        }
        return sb.toString();
    }

    public void removeFromBoard() {
        tiles.forEach(tile->{
            boardClass.claerByIndex(tile.cordX, tile.cordY);
        });
        tiles.clear();
        draw();
    }

    private void coloriseHandToBasicColor() {
        paneList.forEach(pane -> {
            pane.setBackground(new Background(new BackgroundFill(Color.rgb(227,200,141), CornerRadii.EMPTY, Insets.EMPTY)));
            pane.setStyle("-fx-background-radius: 15px");
        });
        paneList.clear();
        draw();
    }
    private void handleTryPlaceWord() {
        StringBuilder sb = new StringBuilder();
        tiles.forEach(t->{
            sb.append(t.letter).append(",").append(t.cordX).append(",").append(t.cordY).append("\n"); // split by "\n"
        });

        // checking if horizontal to vertical
        sb.append(checkDirection());
        //building our string
        String val = sb.toString();
        System.out.println("sb.toString() = " + val);
        sendValueToVM(val);
        //clearing the list after sending the changes to the vm
        tiles.clear();
    }

    private String checkDirection() {
        //first tile indexes
        int firstX = tiles.get(0).cordX;
        int firstY = tiles.get(0).cordY;

        //second tile indexes
        int seccondX = tiles.get(1).cordX;
        int seccondY = tiles.get(1).cordY;

        if(firstX==seccondY && seccondY!=seccondX)
            return "H";
        else if(firstY==seccondY && seccondX!=seccondY)
            return "V";
        else
            return "cannot find if the word is vertical or horizontal";
    }

    private void sendValueToVM(String val) {
        ViewModel.getSharedInstance().updateTryPlaceWordInViewModel(val);
    }

    private void handleId() {
        String id = this.id.get();
        switch (id){
            case "1":
                player1.setText(MenuController.getNickName().get());
                player2.setVisible(false);
                player3.setVisible(false);
                player4.setVisible(false);
                break;
            case "2":
                player2.setText(MenuController.getNickName().get());
                player1.setVisible(false);
                player3.setVisible(false);
                player4.setVisible(false);
                break;
            case "3":
                player3.setText(MenuController.getNickName().get());
                player2.setVisible(false);
                player1.setVisible(false);
                player4.setVisible(false);
                break;
            case "4":
                player4.setText(MenuController.getNickName().get());
                player2.setVisible(false);
                player3.setVisible(false);
                player1.setVisible(false);
                break;
        }
    }

    private void removeFromHand() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <handString.get().length() ; i++) {
            if (!paneHandList.contains(handString.get().charAt(i))) {
                sb.append(handString.get().charAt(i));
            }
        }
        handChanges=sb.toString();
        ViewModel.getSharedInstance().setNewHand(sb.toString());
        paneHandList.clear();
    }


    public void drawHand() {
        HandHbox.getChildren().clear();

        BackgroundFill backgroundFill = new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        String[] splitString = handString.get().split(",");

        for (int i = 0; i < splitString.length; i+=2) {
            String letterStr= splitString[i];
            String scoreStr=splitString[i+1];
            Pane handPane = new Pane();
            handPane.setId("handPane");
            handPane.setBackground(background);
            Label score = new Label();
            Label letter = new Label();

            letter.setText(letterStr);
            score.setText(scoreStr);

            Insets labelsPadding = new Insets(47);
            score.setPadding(labelsPadding);
            letter.setPadding(new Insets(10));

            handPane.setStyle("fx-border-color: black; -fx-border-width: 1px;");

            handPane.getChildren().addAll(score, letter);

            HandHbox.getChildren().add(handPane);

            handPane.setOnMouseClicked(event -> {
                if (handPane.getBackground()!=null && handPane.getBackground().equals(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)))) {
                    letterFromHand="";
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setContentText("You already used this tile!\nTry a different tile.");
                    alert.showAndWait();
                } else {
                    paneList.add(handPane);
                    handPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    handPane.setStyle("-fx-background-radius: 15px; -fx-background-color: blue; -fx-effect: dropshadow(gaussian, #0a45ea, 20, 0, 2, 2);");
                    paneHandList.add(letterStr);
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
                StackPane pane = new StackPane();
                pane.setMinSize(40, 40);
                if(boardClass.getBoard()[i][j] != null) {
                    Label l = new Label();
                    l.setText(boardClass.getBoard()[i][j]);
                    pane.getChildren().add(l);
                }
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
                        if(pane.getChildren().equals("3W") || pane.getChildren().equals("2W") || pane.getChildren().equals("2L") ||
                                pane.getChildren().equals("3L") || pane.getChildren().equals("Start") );{
                            pane.getChildren().clear();}
                        if(pane.getChildren().isEmpty())
                            pane.getChildren().add(letter);
                        confirmChangesBTN.setVisible(true);
                        //adding the new tile to the list
                        tiles.add(new ConfrimTiles(x,y,letterFromHand));
                    }
                    letterFromHand="";
                });
                row.getChildren().add(pane);
            }
            VboxBoard.getChildren().add(row);
        }
    }

    private void setColor(StackPane pane, int i, int j) {
        pane.setId("score");
        // red part
        if((i==0 && j==0) ||(i==0 && j==7) ||(i==0 && j==14) || (i==7 && j==14) ||(i==7 && j==14) || (i==14 && j==14)|| (i==14 && j==7)|| (i==14 && j==0)|| (i==7 && j==0) ) {
            pane.setStyle("-fx-background-color: red");
            pane.setAlignment(Pos.CENTER);
            Label label= new Label();
            label.setText("3W");
            pane.getChildren().add(label);
        }

        // light blue part
      else if((i==0 && j==3) || (i==0 && j==11) || (i==2 && j==6) || (i==2 && j==8) || (i==3 && j==7) || (i==3 && j==0) || (i==3 && j==14) ||
                (i==6 && j==2) || (i==6 && j==6) || (i==6 && j==8) || (i==7 && j==3) || (i==7 && j==11) || (i==8 && j==2) || (i==8 && j==6)
                || (i==8 && j==8) || (i==8 && j==12) || (i==11 && j==0) || (i==11 && j==7) || (i==11 && j==14) || (i==12 && j==6) ||
                (i==12 && j==8) || (i==14 && j==3) ||(i==14 && j==11)) {
            pane.setStyle("-fx-background-color:  #00ffea;-fx-border-color: black; -fx-border-width: 1px;");
            pane.setAlignment(Pos.CENTER);
            Label label= new Label();
            label.setText("2L");
            pane.getChildren().add(label);
        }

        // The blue part
        else if((i==1 && j==5) || (i==1 && j==9) ||(i==5 && j==1) ||(i==5 && j==5) ||(i==5 && j==9) || (i==5 && j==13) ||
                (i==9 && j==1) ||(i==9 && j==5) ||(i==9 && j==9) ||(i==9 && j==13) || (i==13 && j==5) || (i==13 && j==9)) {
            pane.setStyle("-fx-background-color: blue;-fx-border-color: black; -fx-border-width: 1px;");
            pane.setAlignment(Pos.CENTER);
            Label label= new Label();
            label.setText("3L");
            pane.getChildren().add(label);
        }

        // yellow part
        else if((i==1 && j==1) ||(i==2 && j==2) ||(i==3 && j==3) || (i==4 && j==4) || (i==1 && j==13) || (i==2 && j==12) ||
                (i==3 && j==11) || (i==4 && j==10) || (i==10 && j==4) ||(i==11 && j==3) || (i==12 && j==2) ||
                (i==13 && j==1) || (i==10 && j==10) || (i==11 && j==11) || (i==12 && j==12) ||(i==13 && j==13)) {
            pane.setStyle("-fx-background-color: #e3e362;-fx-border-color: black; -fx-border-width: 1px;");
            pane.setAlignment(Pos.CENTER);
            Label label= new Label();
            label.setText("2W");
            pane.getChildren().add(label);

        }

        // middle part
        else if((i==7 && j==7)) {
            pane.setStyle("-fx-background-color: #e3e362;-fx-border-color: black; -fx-border-width: 1px;");
            pane.setAlignment(Pos.CENTER);
            Label label= new Label();
            label.setText("Star");
            pane.getChildren().add(label);

        }
        else {
            pane.setStyle("-fx-background-color: grey;-fx-border-color: black; -fx-border-width: 1px;");
            pane.setAlignment(Pos.CENTER);
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

    public static BoardClass getBoardClass() {
        return boardClass;
    }
    // inner class that will be used after placing all the words on the board
    public class ConfrimTiles{
        private int cordX;
        private int cordY;
        private String letter;

        public ConfrimTiles(int cordX, int cordY, String letter) {
            this.cordX = cordX;
            this.cordY = cordY;
            this.letter = letter;
        }

    }
}

