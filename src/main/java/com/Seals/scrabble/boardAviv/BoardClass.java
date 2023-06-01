package com.Seals.scrabble.boardAviv;

public class BoardClass {
    int width;
    int height;

    public void setBoard(int i, int j,int val) {
        try {
            if (i < 0 || i > width)
                throw new Exception("Index i is out of bounds!");
            if (j < 0 || j > height)
                throw new Exception("Index j is out of bounds!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.board[i][j]=val;
    }

    public int[][] getBoard() {
        return board;
    }

    int[][] board;

    public BoardClass(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public int getState(int i, int j){
        try {
            if (i < 0 || i > width)
                throw new Exception("Index i is out of bounds!");
            if (j < 0 || j > height)
                throw new Exception("Index j is out of bounds!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return this.board[i][j];
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

//    public void stepFoword(int x, int y){ //step on the board
//        board[x][y] = 1;
//    }
//
//    public void stepBack(int x, int y){ // set the place in the matrix to 0 because we left
//        board[x][y] = 0;
//    }


//    public static void main(String[] args) {
//        BoardClass board = new BoardClass(8, 6);
//        board.stepFoword(2, 2);
//        board.stepFoword(3, 2);
//        board.stepFoword(4, 2);
//        board.printBoard();
//
//        board.stepBack(2,2);
//        board.stepBack(3,2);
//        board.stepBack(4,2);
//        board.printBoard();
//    }

}
