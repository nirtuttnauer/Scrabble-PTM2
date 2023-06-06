package com.Seals.scrabble.factories.command.commands.gamecommands;

import com.Seals.scrabble.factories.command.ICommand;
import com.Seals.scrabble.model.hostSide.game.GameManager;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class PlaceCommand implements ICommand {

    @Override
    public String execute(String[] args) {
        try {
            boolean success = getGameManager().tryPlaceWordAction(getGameManager().getPlayerManager().getPlayer(getGameManager().getCurrentPlayer().getId()), args);
            return success ? "Word successfully placed!" : "Word placement failed.";
        } catch (IllegalArgumentException e) {
            System.out.println("error");
            return e.getMessage();
        }
    }
}
