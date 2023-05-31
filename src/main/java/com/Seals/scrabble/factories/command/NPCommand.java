package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;

import static com.Seals.scrabble.model.hModel.getGameManager;

class NPCommand implements ICommand {
    private final GameManager gm;

    public NPCommand() {
        this.gm = getGameManager();
    }

    @Override
    public String execute(String[] args) {
        if (gm.getTotalPlayers() < 4) {
            return String.valueOf(gm.addPlayer());
        } else {
            System.out.println("Player limit reached");
            return "0";
        }
    }
}
