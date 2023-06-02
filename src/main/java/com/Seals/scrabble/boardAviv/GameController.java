package com.Seals.scrabble.boardAviv;

import com.Seals.scrabble.controller.iController;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.ResourceBundle;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public class GameController  implements  Observer , iController {
    // Add your game-related logic and event handlers here
    @FXML
    Canvas BoardCanvas;

    private BoardClass boardClass;

    private Affine affine;

    @FXML
    public void initialize(){
        this.affine=new Affine();
        this.affine.appendScale(400/10,400/10);
        this.boardClass= new BoardClass(10,10);
        draw();
    }


    public GameController() {
    }


    public void printBoard(){
        System.out.println("---");
        for (int i = 0; i < this.boardClass.height; i++) {
            String line = "|";
            for (int j = 0; j < this.boardClass.width; j++) {
                if (this.boardClass.getBoard()[j][i] == 0)
                    line += ".";
                else
                    line += "*";
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("---\n");
    }

    public void stepFoword(int x, int y){ //step on the board
       this.boardClass.setBoard(x,y,1);
       draw();
    }

    public void stepBack(int x, int y){ // set the place in the matrix to 0 because we left
        this.boardClass.setBoard(x,y,0);
        draw();
    }

    public void handleSteps(int x , int y){
        if(this.boardClass.getState(x,y)==0)
            stepFoword(x,y);
        else
            stepBack(x,y);
    }





    public void onFinishButtonClick(ActionEvent event) {
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ViewModel){
            System.out.println("Update from" + this.getClass().toString());
        }
    }

    @Override
    public void onMenuButtonClick() throws IOException {
    setScene("MainView");
    }

    @Override
    public void onExitButtonClick() throws IOException {
        Platform.exit();
    }

    public void draw(){
        GraphicsContext g = this.BoardCanvas.getGraphicsContext2D();
        g.setTransform(this.affine);
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,400,400);
        g.setFill(Color.BLACK);

        for (int i = 0; i < this.boardClass.width; i++) {
            for (int j = 0; j < this.boardClass.height; j++) {
                if(this.boardClass.getState(i,j)==1)
                g.fillRect(i,j,1,1);
            }
        }
        g.setStroke(Color.GREY);
        g.setLineWidth(0.05);
        for (int i = 0; i <=this.boardClass.width ; i++) {
            g.strokeLine(i,0,i,10);
        }

        for (int j = 0; j <=this.boardClass.height ; j++) {
            g.strokeLine(0,j,10,j);
        }
    }

    public void onDrawBtnClicked(){
        for (int i = 0; i <5 ; i++) {
            Random randoWidth= new Random();
            Random randomHieght = new Random();
            handleSteps(randoWidth.nextInt(boardClass.width),randomHieght.nextInt(boardClass.height));
        }
        draw();
    }



    @FXML
    private void handleDraw(MouseEvent mouseEvent) {
        double mouseX= mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        // show the point exactly
        try {
            Point2D cordinate = this.affine.inverseTransform(mouseX, mouseY);
            //we have to cast to int because we have a int[][] board!
            int simX = (int) cordinate.getX();
            int simY = (int) cordinate.getY();

            System.out.println(simX+","+simY);

            handleSteps(simX,simY);
        } catch (NonInvertibleTransformException e) {
            System.out.println("Could not invert transform");
        }
    }
}