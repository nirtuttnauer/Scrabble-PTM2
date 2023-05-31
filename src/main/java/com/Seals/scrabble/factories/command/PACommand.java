package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;

class PACommand implements ICommand {
    private final GameManager gm;

    public PACommand(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public String execute(String[] args) {
        gm.performAction("PA", Integer.parseInt(args[1]), null);
        gm.getGameBoard().printBoard();
        return null;
    }
}
