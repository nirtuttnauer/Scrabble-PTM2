package com.Seals.scrabble.factories.command.commands.modelcommands;

import com.Seals.scrabble.factories.command.ICommand;

import java.net.Socket;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class QuitCommand implements ICommand {

    @Override
    public String execute(String... args) {
        if (args.length > 0) {
            int playerId = Integer.parseInt(args[0]); // assuming first argument is the player's ID
            getGameManager().getPlayerManager().removePlayerById(playerId);
            return "Player " + playerId + " has quit the game.";
        }
        return null;
    }
}
