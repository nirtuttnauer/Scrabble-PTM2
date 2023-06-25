package com.Seals.scrabble.factories.command.commands.gamecommands;

import com.Seals.scrabble.factories.command.ICommand;

import com.Seals.scrabble.model.hostSide.game.Player;


import java.net.Socket;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class NewPlayerCommand implements ICommand {
    //NP,NickName


@Override
public String execute(String... args) {
    if (args.length > 0) {
        Player p = new Player(args[0]);
        boolean added = getGameManager().addPlayer(p);
        if (added) {
            return "ID:"+p.getId();
        } else {
            System.out.println("Failed to add player");
            return "0";
        }
    } else {
        System.out.println("No player nickname provided");
        return "0";
    }
}



}
