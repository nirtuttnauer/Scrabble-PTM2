package com.Seals.scrabble.factories.command.commands.gamecommands;

import com.Seals.scrabble.factories.command.ICommand;

import java.net.Socket;

import static com.Seals.scrabble.model.hModel.getGameManager;

public class PassCommand implements ICommand {

    @Override
    public String execute(String... args) {
        return "pass";
    }
}
