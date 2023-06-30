package com.Seals.scrabble.factories.command.commands.gamecommands;

import com.Seals.scrabble.factories.command.ICommand;
import com.Seals.scrabble.model.hostSide.game.GameManager;

import java.net.Socket;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class PlaceCommand implements ICommand {
    @Override
    public String execute(String... s) {
        // Log the received arguments
        System.err.println(s.toString());

        // Get the player ID from the first argument
        String ID = s[0];
        // Get the word from the second argument
        String Word = s[1];
        // Get the x-coordinate from the third argument
        int i = Integer.parseInt(s[2]);
        // Get the y-coordinate from the fourth argument
        int j = Integer.parseInt(s[3]);
        // Get the vertical flag from the fifth argument
        Boolean isVertical = (s[4].equals("V")) ? true : false;

        try {
            // Try to place the word on the board
            System.err.println(Integer.parseInt(ID) + Word + i + j + isVertical);
            boolean success = getGameManager().tryPlaceWordAction(Integer.parseInt(ID), Word, i, j, isVertical);
            getGameManager().getGameServer().broadcast("board," + getGameManager().getGameBoard().printBoardLetters());


            getGameManager().getGameServer().broadcast("scores," + getGameManager());

            // Return a message based on whether the placement was successful
            return success ? "Word successfully placed!" : "Word placement failed.";
        } catch (IllegalArgumentException e) {
            // If an error occurred, log it and return the error message
            System.out.println("error");
            return e.getMessage();
        }

    }

}
