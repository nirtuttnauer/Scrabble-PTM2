package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;

import static com.Seals.scrabble.model.hModel.getGameManager;

class EXCommand implements ICommand {
    private final GameManager gm;

    public EXCommand() {
        this.gm = getGameManager();
    }

    @Override
    public String execute(String[] args) {
        gm.performAction("EX", Integer.parseInt(args[1]), null);
        gm.getGameBoard().printBoard();
        return null;
    }
}
