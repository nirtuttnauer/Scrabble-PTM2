package com.Seals.scrabble.factories.command;


import com.Seals.scrabble.model.hostSide.GameHandler;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class TurnModelCommand implements ICommand {
    @Override
    public String execute(String[] args) {
        getGameManager().getPlayerManager().notifyCurrentPlayerTurn();
        return null;
    }
}
