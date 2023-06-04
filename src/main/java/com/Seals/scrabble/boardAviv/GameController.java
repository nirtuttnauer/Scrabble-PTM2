package com.Seals.scrabble.boardAviv;

import com.Seals.scrabble.controller.iController;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
    private Canvas BoardCanvas;

    @FXML
    HBox HandHbox;
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
        } else
            System.out.println("Hand object didn't create, handString is null");

        this.affine = new Affine();

        this.affine.appendScale(600 / 15, 600 / 15);

        this.boardClass = new BoardClass(15, 15);

        draw();

        if (hand != null)
            drawHand();
    }

    public void stepForward(int x, int y, String letter) {
        this.boardClass.setBoard(x, y, letter);
        draw();
    }

    public void stepBack(int x, int y) {
        this.boardClass.setBoard(x, y, null);
        draw();
    }

    public void handleSteps(int x, int y, String letter) {
        if (this.boardClass.getState(x, y) == null)
            stepForward(x, y, letter);
        else
            stepBack(x, y);
    }

    public void onFinishButtonClick(ActionEvent event) {
        // Handle finish button click event
    }

    public void drawHand() {
        HandHbox.getChildren().clear(); // Clear existing children

        BackgroundFill backgroundFill = new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY);

        // Create a new Background with the BackgroundFill
        Background background = new Background(backgroundFill);

        for (int i = 0; i < hand.tiles.length; i++) {
            GridPane pane = new GridPane();
            Label score = new Label();
            Label letter = new Label();

            // Setting the grid background
            pane.setBackground(background);

            letter.setText(hand.tiles[i]);
            score.setText("0");

            // Set padding around the labels
            Insets labelPadding = new Insets(47);

            score.setPadding(labelPadding);
            letter.setPadding(new Insets(10));

            // Show the lines inside the grid
            pane.setGridLinesVisible(true);

            pane.getChildren().addAll(score, letter);

            pane.setPrefSize(HandHbox.getWidth() / hand.tiles.length, Double.MAX_VALUE);

            HandHbox.getChildren().add(pane);
        }
    }

    public void draw() {
        GraphicsContext g = this.BoardCanvas.getGraphicsContext2D();
        g.setTransform(this.affine);
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 600, 600);
        g.setFill(Color.BLACK);

        for (int i = 0; i < this.boardClass.getBoard().length; i++) {
            for (int j = 0; j < this.boardClass.getBoard()[i].length; j++) {
                String letter = this.boardClass.getState(i, j);
                if (letter != null)
                    g.strokeText(letter, i + 0.5, j + 0.8);
            }
        }

        g.setStroke(Color.GREY);
        g.setLineWidth(0.05);
        for (int i = 0; i <= this.boardClass.getBoard().length; i++) {
            g.strokeLine(i, 0, i, 15);
        }

        for (int j = 0; j <= this.boardClass.getBoard()[0].length; j++) {
            g.strokeLine(0, j, 15, j);
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
