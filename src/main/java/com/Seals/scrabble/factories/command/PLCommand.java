package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;

class PLCommand implements ICommand {
    private final GameManager gm;

    public PLCommand(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public String execute(String[] args) {
        gm.performAction("PL", Integer.parseInt(args[1]), args[2]);
        gm.getGameBoard().printBoard();
        return null;
    }
}
