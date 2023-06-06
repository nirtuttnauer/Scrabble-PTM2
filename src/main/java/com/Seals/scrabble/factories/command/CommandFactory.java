package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.factories.command.commands.gamecommands.ExchangeCommand;
import com.Seals.scrabble.factories.command.commands.gamecommands.NewPlayerCommand;
import com.Seals.scrabble.factories.command.commands.gamecommands.PassCommand;
import com.Seals.scrabble.factories.command.commands.gamecommands.PlaceCommand;
import com.Seals.scrabble.factories.command.commands.hostcommands.TurnUpdateCommand;
import com.Seals.scrabble.factories.command.commands.modelcommands.QuitCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<String, ICommand> commands;

    public CommandFactory() {
        commands = new HashMap<>();

        // Add commands here
        commands.put("PL", new PlaceCommand());
        commands.put("PA", new PassCommand());
        commands.put("EX", new ExchangeCommand());
        commands.put("NP", new NewPlayerCommand());
        commands.put("QU", new QuitCommand());
        commands.put("TU", new TurnUpdateCommand());
    }

    public ICommand getCommand(String commandName) {
        return commands.get(commandName);
    }
}
