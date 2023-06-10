package com.Seals.scrabble.boardAviv;

public class BoardClass {
    private int width;
    private int height;
    private String[][] board;

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
}
