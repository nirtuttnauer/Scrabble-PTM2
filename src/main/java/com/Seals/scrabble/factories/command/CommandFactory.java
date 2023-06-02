package com.Seals.scrabble.factories.command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, ICommand> commands;

    public CommandFactory() {
        commands = new HashMap<>();
        //player commands
        commands.put("PL", new PlaceCommand());
        commands.put("PA", new PassCommand());
        commands.put("EX", new ExchangeCommand());
        commands.put("NP", new NewPlayerCommand());
        //turn commands
        commands.put("TM", new TurnModelCommand());
        commands.put("TH", new TurnHostCommand());
        //gameboard update commands
        commands.put("UP", new UpdateCommand());
        //
    }

    public ICommand getCommand(String commandName) {
        return commands.get(commandName);
    }
}
