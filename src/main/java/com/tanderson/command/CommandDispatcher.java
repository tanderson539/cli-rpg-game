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
    private final GameContext context;

    public CommandDispatcher(GameContext context) {
        this.commands = new HashMap<>();

        this.context = context;

        this.createCommandsMap();
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

        Command command = this.commands.get(tokens[0]);

        if (command != null) {
            return command.execute(tokens, this.context);
        } else {
            return "Invalid command";
        }
    }

    /**
     * Private helper method to add all necessary commands to the commands HashMap
     */
    private void createCommandsMap() {
        commands.put("hi", new HelloCmd());
        commands.put("hello", new HelloCmd());

        commands.put("exit", new ExitCmd());
        commands.put("quit", new ExitCmd());
        commands.put("x", new ExitCmd());

        commands.put("help", new HelpCmd());

        commands.put("level", new SkillLevelCmd());
        commands.put("lvl", new SkillLevelCmd());

        commands.put("recipes", new RecipesCmd());
        commands.put("rs", new RecipesCmd());

        commands.put("mine", new PlayerMineCmd());
        commands.put("m", new PlayerMineCmd());

        commands.put("inventory", new PlayerInvCmd());
        commands.put("inv", new PlayerInvCmd());
        commands.put("i", new PlayerInvCmd());

        commands.put("drop", new DropItemCmd());

        commands.put("rand", new RandCmd());

        commands.put("dev", new DevModeCmd());

        commands.put("loot", new LootCmd());

        commands.put("craft", new CraftCmd());

        commands.put("tile", new MapTileCommand());

        commands.put("map", new RandomMapCommand());
    }
}
