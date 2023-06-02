package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;

import static com.Seals.scrabble.model.hModel.getGameManager;

class PlaceCommand implements ICommand {
    private final GameManager gm;

    public PlaceCommand() {
        this.gm = getGameManager();
    }

    @Override
    public String execute(String[] args) {
        gm.tryPlaceWordAction(gm.getPlayerManager().getPlayer(gm.getCurrentPlayer().getId()), args);
        gm.getGameBoard().printBoard();
        return null;
    }
}
