package com.Seals.scrabble.boardAviv;

import com.Seals.scrabble.controller.iController;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public class GameController implements Observer, iController {
    @FXML
    private GridPane boardGrid;

    @FXML
    private GridPane handPane;

    private BoardClass boardClass;
    private Affine affine;

    private StringProperty handString;

    private Hand hand;

    @FXML
    public void initialize() {
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

        this.boardGrid.setGridLinesVisible(true);

        if (hand != null) {
            drawHand();
        }

        boardGrid.setPrefWidth(500.0);
        boardGrid.setPrefHeight(400.0);
    }

    public void stepForward(int x, int y, String letter) {
        this.boardClass.setBoard(x, y, letter);
    }

    public void stepBack(int x, int y) {
        this.boardClass.setBoard(x, y, null);
    }

    public void handleSteps(int x, int y, String letter) {
        if (this.boardClass.getState(x, y) == null) {
            stepForward(x, y, letter);
        } else {
            stepBack(x, y);
        }
    }

    public void onFinishButtonClick(ActionEvent event) {
        // Handle finish button click event
    }

    public void drawHand() {
        handPane.getChildren().clear();

        BackgroundFill backgroundFill = new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        handPane.setBackground(background);

        for (int i = 0; i < hand.tiles.length; i++) {
            Label score = new Label();
            Label letter = new Label();

            letter.setOnDragDetected(this::handleDragDetected);
            letter.setText(hand.tiles[i]);
            score.setText("0");

            Insets labelsPadding = new Insets(47);
            score.setPadding(labelsPadding);
            letter.setPadding(new Insets(10));

            handPane.addColumn(i, score, letter);
            handPane.setMinHeight(100.0);
        }

        handPane.setGridLinesVisible(true);
    }

    @FXML
    private void handleDragDetected(MouseEvent event) {
        Label letterLabel = (Label) event.getSource();
        String letter = letterLabel.getText();

        Dragboard dragboard = letterLabel.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(letter);
        dragboard.setContent(content);

        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        dragboard.setDragView(letterLabel.snapshot(snapshotParameters, null));

        event.consume();
    }

    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getGestureSource() != event.getTarget() && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }

        event.consume();
    }

    @FXML
    private void handleDragDropped(DragEvent event) {
        Label targetCell = (Label) event.getTarget();
        int columnIndex = GridPane.getColumnIndex(targetCell);
        int rowIndex = GridPane.getRowIndex(targetCell);

        double mouseX = event.getX();
        double mouseY = event.getY();

        try {
            Affine inverse = this.affine.createInverse();
            double[] point = new double[]{mouseX, mouseY};
            inverse.transform2DPoints(point, 0, point, 0, 1);
            int simX = (int) Math.floor(point[0]);
            int simY = (int) Math.floor(point[1]);

            handleSteps(simX, simY, event.getDragboard().getString());
            draw();
        } catch (NonInvertibleTransformException e) {
            System.out.println("Could not invert transform");
        }

        event.setDropCompleted(true);
        event.consume();
    }

    private void draw() {
        boardGrid.getChildren().clear();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (boardClass.getBoard()[i][j] != null) {
                    Label label = new Label();
                    label.setText(boardClass.getBoard()[i][j]);

                    // Set the cell's position in the GridPane
                    GridPane.setRowIndex(label, i);
                    GridPane.setColumnIndex(label, j);

                    // Set the cell's size
                    label.setMinSize(40, 40);

                    boardGrid.getChildren().add(label);
                }
            }
        }
    }



    @FXML
    private void handleDraw(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        try {
            Affine inverse = this.affine.createInverse();
            double[] point = new double[]{mouseX, mouseY};
            inverse.transform2DPoints(point, 0, point, 0, 1);
            int simX = (int) Math.floor(point[0]);
            int simY = (int) Math.floor(point[1]);

            System.out.println(simX + "," + simY);

            handleSteps(simX, simY, "A"); // Replace "A" with the desired letter
        } catch (NonInvertibleTransformException e) {
            System.out.println("Could not invert transform");
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
}
