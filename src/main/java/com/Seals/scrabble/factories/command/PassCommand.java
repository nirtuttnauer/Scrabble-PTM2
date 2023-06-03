package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;

import static com.Seals.scrabble.model.hModel.getGameManager;

class PassCommand implements ICommand {
    private final GameManager gm;

    public PassCommand() {
        this.gm = getGameManager();
    }

    @Override
    public String execute(String[] args) {
        gm.getTurnManager().nextTurn();
        gm.getGameBoard().printBoard();
        return null;
    }
}
