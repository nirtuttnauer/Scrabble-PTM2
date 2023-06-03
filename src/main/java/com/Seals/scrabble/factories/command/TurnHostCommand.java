package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class TurnHostCommand implements ICommand {
    @Override
    public String execute(String[] args) {
        // Get the singleton instance of GameManager
        GameManager gm = getGameManager();
        // assuming args[0] is the player ID
        int currentPlayerID = Integer.parseInt(args[0]);

        String message = "Your turn," + currentPlayerID;
        gm.sendMessageToPlayer(currentPlayerID, message);
        return String.valueOf(currentPlayerID);
    }
}
