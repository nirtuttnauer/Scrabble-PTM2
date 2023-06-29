package com.Seals.scrabble.boardAviv;

import java.util.Arrays;

public class BoardClass {
    private int width;
    private int height;
    private  String[][] board;

    public BoardClass(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new String[width][height];
    }

    public void setBoard(int i, int j, String val) {
        validateIndices(i, j);
        this.board[i][j] = val;
    }

    public String[][] getBoard() {
        return board;
    }

    public String getState(int i, int j) {
        validateIndices(i, j);
        return this.board[i][j];
    }

    public void clearByIndex(int i , int j){
        board[i][j] = "";
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void printBoard() {
        System.out.println("---");
        for (int i = 0; i < height; i++) {
            String line = "|";
            for (int j = 0; j < width; j++) {
                if (this.board[i][j] == null)
                    line += ".";
                else
                    line += this.board[i][j];
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("---\n");
    }

    private void validateIndices(int i, int j) {
        if (i < 0 || i >= width)
            throw new IllegalArgumentException("Index i is out of bounds!");
        if (j < 0 || j >= height)
            throw new IllegalArgumentException("Index j is out of bounds!");
    }

    public void setAll(String newValue) {
        String[] lines = newValue.split(" ");
        String[][] newBoard = new String[width][height];
        for (int i = 0; i < width; i++) {
            String[] thisLine= lines[i].split("");
            for (int j = 0; j < thisLine.length; j++) {
                if(thisLine[i].equals("0")){
                    newBoard[i][j]="";
                }
                else{
                    newBoard[i][j]=thisLine[j];
                }
            }
        }
        System.out.println(" the new board is : " + Arrays.deepToString(newBoard));
        board=newBoard;
    }

    public boolean thereIsLetter(int i, int j) {
        if((i<width || i>width) || (j>height || j<height))
            return false;
        return board[i][j].equals("");
    }
}
