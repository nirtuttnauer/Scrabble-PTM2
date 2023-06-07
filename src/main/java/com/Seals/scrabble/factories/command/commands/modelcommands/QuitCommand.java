package com.Seals.scrabble.factories.command.commands.modelcommands;

import com.Seals.scrabble.factories.command.ICommand;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class QuitCommand implements ICommand {
    //QU,ID
   @Override
    public String execute(String args) {
        if (!args.isEmpty()) {
            int playerId = Integer.parseInt(args); // assuming first argument is the player's ID
            getGameManager().getPlayerManager().removePlayerById(playerId);
            return "Player " + playerId + " has quit the game.";
        }
        return null;
    }
}
