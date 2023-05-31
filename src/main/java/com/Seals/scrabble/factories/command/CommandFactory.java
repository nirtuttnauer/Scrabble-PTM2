package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;

import java.util.HashMap;
import java.util.Map;

// Create a CommandFactory class to generate command instances
public class CommandFactory {
    private final Map<String, ICommand> commands;

    public CommandFactory(GameManager gm) {
        commands = new HashMap<>();
        commands.put("PL", new PLCommand(gm));
        commands.put("PA", new PACommand(gm));
        commands.put("EX", new EXCommand());
        commands.put("NP", new NPCommand());
    }

    public ICommand getCommand(String commandName) {
        return commands.get(commandName);
    }
}
