package com.Seals.scrabble.factories.command.commands.gamecommands;

import com.Seals.scrabble.factories.command.ICommand;

import com.Seals.scrabble.model.hostSide.game.Player;



import static com.Seals.scrabble.model.hModel.getGameManager;

public class NewPlayerCommand implements ICommand {
    //NP,NickName
    @Override
    public String execute(String[] args) {
        Player p = getGameManager().addPlayer();
        if (p != null) return String.valueOf(p.getId());
        return null;
    }
}
