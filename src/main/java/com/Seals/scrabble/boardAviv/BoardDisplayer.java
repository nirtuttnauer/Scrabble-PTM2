//package com.Seals.scrabble.boardAviv;
//
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//
//public class BoardDisplayer extends VBox {
//    private Button stepButton;
//    private Canvas canvas;
//
//    public BoardDisplayer() {
//        stepButton= new Button("step");
//        canvas= new Canvas(400,400);
//
//        this.getChildren().addAll(this.stepButton,this.canvas); // for adding the BTN and the canvas
//    }
//    public void draw(){
//        GraphicsContext g = this.canvas.getGraphicsContext2D();;
//        g.setFill(Color.LIGHTGRAY);
//        g.fillRect(0,0,400,400);
//    }
//}
