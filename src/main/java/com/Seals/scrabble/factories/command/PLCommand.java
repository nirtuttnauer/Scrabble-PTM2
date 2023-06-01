package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;

class PLCommand implements ICommand {
    private final GameManager gm;

    public PLCommand() {
        this.gm = new GameManager();
    }

    @Override
    public String execute(String[] args) {
        gm.performAction("PL", Integer.parseInt(args[1]), new String[]{args[2]});
        gm.getGameBoard().printBoard();
        return null;
    }
}
