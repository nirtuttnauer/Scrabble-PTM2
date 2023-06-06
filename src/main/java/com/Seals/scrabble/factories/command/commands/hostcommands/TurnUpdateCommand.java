package com.Seals.scrabble.factories.command.commands.hostcommands;


import com.Seals.scrabble.factories.command.ICommand;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class TurnUpdateCommand implements ICommand {
    @Override
    public String execute(String[] args) {
        getGameManager().getGameHandler().getOut().println("TU:" + getGameManager().getTurnManager().getCurrentPlayerIndex());
        return null;
    }
}
