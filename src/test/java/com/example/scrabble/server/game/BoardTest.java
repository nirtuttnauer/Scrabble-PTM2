package com.example.scrabble.server.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.getBoard();
    }

    @AfterEach
    void tearDown() {
        board = null;
    }

    @Test
    void getBoard() {
        assertNotNull(board);
    }

    @Test
    void boardLegal() {
        Word horn=new Word(get("HORN"), 7, 5, false);
        assertTrue(board.boardLegal(horn));
    }

    @Test
    void tryPlaceWord() {
        Word word = new Word(new Tile[1], 0, 0, true);
        int score = board.tryPlaceWord(word);
        assertEquals(0, score);
    }

    @Test
    void printBoard() {
        assertDoesNotThrow(() -> board.printBoard());
    }

    @Test
    void printBoardLetters() {
        assertDoesNotThrow(() -> board.printBoardLetters());
    }

    @Test
    void printBoardScores() {
        assertDoesNotThrow(() -> board.printBoardScores());
    }

    @Test
    void printBoardBonus() {
        assertDoesNotThrow(() -> board.printBoardBonus());
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("com.example.scrabble.server.game.BoardTest");
    }
    private static Tile[] get(String s) {
		Tile[] ts=new Tile[s.length()];
		int i=0;
		for(char c: s.toCharArray()) {
			ts[i]= Tile.Bag.getBag().getTile(c);
			i++;
		}
		return ts;
	}
}
