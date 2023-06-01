package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;

import static com.Seals.scrabble.model.hModel.getGameManager;

class PACommand implements ICommand {
    private final GameManager gm;

    public PACommand() {
        this.gm = getGameManager();
    }

    @Override
    public String execute(String[] args) {
        gm.performAction("PA", Integer.parseInt(args[1]), null);
        gm.getGameBoard().printBoard();
        return null;
    }
}
