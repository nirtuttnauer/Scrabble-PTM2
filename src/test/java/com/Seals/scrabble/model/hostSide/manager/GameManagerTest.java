package com.Seals.scrabble.model.hostSide.manager;

import com.Seals.scrabble.model.hostSide.game.Board;
import com.Seals.scrabble.model.hostSide.game.GameManager;
import com.Seals.scrabble.model.hostSide.game.Tile;
import com.Seals.scrabble.model.hostSide.game.Tile.Bag;
import com.Seals.scrabble.model.hostSide.game.Word;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameManagerTest {
    private GameManager gameManager;

    @Before
    public void setUp() {
        gameManager = new GameManager();
    }

    @Test
    public void testStartGame() {
        gameManager.startGame();
        // Add assertions to verify the expected behavior
        // For example, you can check if the game has started correctly
    }

    @Test
    public void testEndGame() {
        gameManager.endGame();
        // Add assertions to verify the expected behavior
        // For example, you can check if the game has ended correctly
    }

    @Test
    public void testPlaceValidWord() {
        Board board = gameManager.getGameBoard();
        // Set up the board state with some existing words

        Bag bag = Bag.getBag();
        Tile[] ts = new Tile[10];
        for (int i = 0; i < ts.length; i++)
            ts[i] = bag.getRand();

        Word horn = new Word(ts, 7, 5, false);
        assertTrue(gameManager.placeWord(horn));
        // Add assertions to verify the expected behavior
        // For example, you can check if the word was placed successfully and the score is correct
    }

    @Test
    public void testPlaceInvalidWord() {
        Board board = gameManager.getGameBoard();
        // Set up the board state with some existing words

        Bag bag = Bag.getBag();
        Tile[] ts = new Tile[10];
        for (int i = 0; i < ts.length; i++)
            ts[i] = bag.getRand();

        Word bit = new Word(ts, 10, 4, false);
        assertFalse(gameManager.placeWord(bit));
        // Add assertions to verify the expected behavior
        // For example, you can check if the word placement is rejected and an error message is printed
    }

    @Test
    public void testBag() {
        Bag b = Bag.getBag();
        Bag b1 = Bag.getBag();
        assertSame(b1, b);
        // Add assertions to verify the expected behavior
        // For example, you can check if the Bag is a Singleton

        int[] q0 = b.getQuantities();
        q0[0] += 1;
        int[] q1 = b.getQuantities().clone();
        assertArrayEquals(q0, q1);
        // Add assertions to verify the expected behavior
        // For example, you can check if the getQuantities method returns a clone

        for (int k = 0; k < 9; k++) {
            int[] qs = b.getQuantities().clone();
            Tile t = b.getRand();
            int i = t.letter - 'A';
            int[] qs1 = b.getQuantities().clone();
            assertEquals(qs[i] - 1, qs1[i]);
            // Add assertions to verify the expected behavior
            // For example, you can check if the getRand method returns the expected tile and updates the quantities correctly

            b.put(t);
            b.put(t);
            b.put(t);

            assertEquals(qs[i], b.getQuantities()[i]);
            // Add assertions to verify the expected behavior
            // For example, you can check if the put method puts the tile back correctly
        }

        assertNull(b.getTile('a'));
        assertNotNull(b.getTile('A'));
        assertNull(b.getTile('$'));
        // Add assertions to verify the expected behavior
        // For example, you can check if the getTile method returns the correct tiles
    }

    @Test
    public void testBoard() {
        Board b = Board.getBoard();
        assertSame(b, Board.getBoard());
        // Add assertions to verify the expected behavior
        // For example, you can check if the Board is a Singleton

        Bag bag = Bag.getBag();
        Tile[] ts = new Tile[10];
        for (int i = 0; i < ts.length; i++)
            ts[i] = bag.getRand();

        Word w0 = new Word(ts, 0, 6, true);
        Word w1 = new Word(ts, 7, 6, false);
        Word w2 = new Word(ts, 6, 7, true);
        Word w3 = new Word(ts, -1, 7, true);
        Word w4 = new Word(ts, 7, -1, false);
        Word w5 = new Word(ts, 0, 7, true);
        Word w6 = new Word(ts, 7, 0, false);

        assertFalse(b.boardLegal(w0));
        assertFalse(b.boardLegal(w1));
        assertFalse(b.boardLegal(w2));
        assertFalse(b.boardLegal(w3));
        assertFalse(b.boardLegal(w4));
        assertTrue(b.boardLegal(w5));
        assertTrue(b.boardLegal(w6));
        // Add assertions to verify the expected behavior
        // For example, you can check if the boardLegal method returns the correct legality of word placement

        for (Tile t : ts)
            bag.put(t);

        Word horn = new Word(get("HORN"), 7, 5, false);
        assertEquals(14, b.tryPlaceWord(horn));
        // Add assertions to verify the expected behavior
        // For example, you can check if the tryPlaceWord method returns the correct score for the first word

        Word farm = new Word(get("FA_M"), 5, 7, true);
        assertEquals(9, b.tryPlaceWord(farm));
        // Add assertions to verify the expected behavior
        // For example, you can check if the tryPlaceWord method returns the correct score for the second word

        Word paste = new Word(get("PASTE"), 9, 5, false);
        assertEquals(25, b.tryPlaceWord(paste));
        // Add assertions to verify the expected behavior
        // For example, you can check if the tryPlaceWord method returns the correct score for the third word

        Word mob = new Word(get("_OB"), 8, 7, false);
        assertEquals(18, b.tryPlaceWord(mob));
        // Add assertions to verify the expected behavior
        // For example, you can check if the tryPlaceWord method returns the correct score for the fourth word

        Word bit = new Word(get("BIT"), 10, 4, false);
        assertEquals(22, b.tryPlaceWord(bit));
        // Add assertions to verify the expected behavior
        // For example, you can check if the tryPlaceWord method returns the correct score for the fifth word
    }

    private Tile[] get(String s) {
        Tile[] ts = new Tile[s.length()];
        int i = 0;
        for (char c : s.toCharArray()) {
            ts[i] = Tile.Bag.getBag().getTile(c);
            i++;
        }
        return ts;
    }
}
