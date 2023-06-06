package com.Seals.scrabble.factories.command.commands.gamecommands;

import com.Seals.scrabble.factories.command.ICommand;
import com.Seals.scrabble.model.hostSide.game.GameManager;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class ExchangeCommand implements ICommand {
    private final GameManager gm;

    public ExchangeCommand() {
        this.gm = getGameManager();
    }

    @Override
    public String execute(String[] args) {
        gm.getGameBoard().printBoard();
        return null;
    }
}
