package com.Seals.scrabble.factories.command.commands.hostcommands;


import com.Seals.scrabble.factories.command.ICommand;

import java.net.Socket;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class TurnUpdateCommand implements ICommand {

    @Override
    public String execute(Socket socket, String... args) {
        System.out.println("hellppp");
               return getGameManager().getGameServer().broadcast(String.valueOf(getGameManager().getTurnManager().getCurrentPlayerIndex() + 1));

    }
}