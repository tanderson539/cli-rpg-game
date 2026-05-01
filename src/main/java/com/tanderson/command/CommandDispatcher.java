package com.tanderson.command;

import com.tanderson.GameContext;
import com.tanderson.command.commands.*;

import java.util.HashMap;

/**
 * Handles logic to perform and carry out player-run commands
 * Keeps a HashMap of all available commands that can be run.
 * TODO: Make distinction between / commands and normal commands
 * I.e. turn dev into /dev, don't echo out the command, and perform the dev mode command.
 * Same for rand -> /rand, loot -> /loot
 * Essentially, all debug/dev commands turn into / commands.
 */
public class CommandDispatcher {
    private final HashMap<String, Command> commands;
    private final HashMap<String, Command> slashCommands;
    private final GameContext context;

    public CommandDispatcher(GameContext context) {
        this.commands = new HashMap<>();
        this.slashCommands = new HashMap<>();

        this.context = context;
    }

    /**
     * Takes an entire input string, separates it into individual arguments split by a space
     * Uses the first arguments (aka the baseline command), and tries to find that command in the commands
     * HashMap. If the baseline command exists, executes it, and if not, returns invalid.
     * @param cmd A full input string representing a command and all potential arguments
     * @return Returns a string output from the command. Commands can execute many functions, but must return a string.
     */
    public String dispatch(String cmd) {
        String[] tokens = cmd.split(" ");

        Command command;

        if (tokens[0].charAt(0) == '/') {
            command = slashCommands.get(tokens[0].substring(1));
        } else {
            command = commands.get(tokens[0]);
        }

        if (command != null) {
            return command.execute(tokens, this.context);
        } else {

            return "Invalid command";
        }
    }

    /**
     * Adds a command to the regular commands HashMap.
     * @param alias a String representing the command token.
     * @param command A Command object representing the command to be run with a given alias.
     */
    public void registerCommand(String alias, Command command) {
        this.commands.put(alias, command);
    }

    /**
     * Adds a slash command to the slash commands HashMap.
     * @param alias a String representing the command token.
     * @param command A Command object representing the command to be run with a given alias.
     */
    public void registerSlashCommand(String alias, Command command) {
        this.slashCommands.put(alias, command);
    }
}
