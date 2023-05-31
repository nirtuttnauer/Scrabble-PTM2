package com.Seals.scrabble.boardAviv;

import com.Seals.scrabble.controller.iController;
import com.Seals.scrabble.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import static com.Seals.scrabble.factories.SceneFactory.setScene;

public class GameController  implements Initializable, Observer , iController {
    // Add your game-related logic and event handlers here
    @FXML
    Canvas BoardCanvas;

    int width;
    int height;
    int[][] board;

    BoardClass boardClass;
    public GameController(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
        this.boardClass= new BoardClass(10,10);
        this.boardClass.stepFoword(2,2);
        this.boardClass.stepFoword(3,2);
        this.boardClass.stepFoword(4,2);
        this.boardClass.stepFoword(5,5);
    }


    public void printBoard(){
        System.out.println("---");
        for (int i = 0; i < height; i++) {
            String line = "|";
            for (int j = 0; j < width; j++) {
                if (this.board[j][i] == 0)
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
        board[x][y] = 1;
        printBoard();
    }

    public void stepBack(int x, int y){ // set the place in the matrix to 0 because we left
        board[x][y] = 0;
        printBoard();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the game view
    }



    public void onFinishButtonClick(ActionEvent event) {
    }

    public void onMenuButtonClick(ActionEvent event) {
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

    }

    public void draw(){
        GraphicsContext g = this.BoardCanvas.getGraphicsContext2D();;
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,400,400);
        g.setFill(Color.BLACK);

        for (int i = 0; i < this.boardClass.width; i++) {
            for (int j = 0; j < this.boardClass.height; j++) {
                if(this.boardClass.getState(i,j)==1)
                g.fillRect(i,j,1,1);
            }
        }
    }

    public void onDrawBtnClicked(){
        draw();
    }
}