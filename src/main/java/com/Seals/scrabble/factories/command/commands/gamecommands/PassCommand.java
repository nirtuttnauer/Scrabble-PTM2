package com.Seals.scrabble.factories.command.commands.gamecommands;

import com.Seals.scrabble.factories.command.ICommand;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class PassCommand implements ICommand {
    @Override
    public String execute(String args) {
        getGameManager().getTurnManager().nextTurn();
        getGameManager().getGameBoard().printBoard();
        return null;
    }
}
