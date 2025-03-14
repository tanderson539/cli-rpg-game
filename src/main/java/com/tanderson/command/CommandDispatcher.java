package com.tanderson.command;

import com.tanderson.GameContext;
import com.tanderson.command.commands.*;

import java.util.HashMap;

public class CommandDispatcher {
    private final HashMap<String, Command> commands;
    private final GameContext context;

    public CommandDispatcher(GameContext context) {
        this.commands = new HashMap<>();

        this.context = context;

        commands.put("hi", new HelloCmd());

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
    }

    public String dispatch(String cmd) {
        String[] tokens = cmd.split(" ");

        Command command = this.commands.get(tokens[0]);

        if (command != null) {
            return command.execute(tokens, this.context);
        } else {
            return "Invalid command";
        }
    }
}
