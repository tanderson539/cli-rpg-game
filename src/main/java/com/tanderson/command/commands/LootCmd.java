package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredSlashCommand;
import com.tanderson.items.ItemRecord;
import com.tanderson.log.LogLevel;
import com.tanderson.systems.rds.tables.OreItemTable;
import org.apache.commons.lang3.StringUtils;

@RegisteredSlashCommand(aliases = {"loot"})
public class LootCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        if (context.isDevMode()) {
            OreItemTable table;

            if(args.length > 1 && StringUtils.isNumeric(args[1])){
                table = new OreItemTable(Integer.parseInt(args[1]));
                context.getLogger().info("Running loot command with a seed of: " + args[1]);
            } else {
                table = new OreItemTable();
                context.getLogger().info("Running loot command with no explicit seed");
            }

            ItemRecord[] items;

            items = table.runTable();
            context.getLogger().info("Running loot table...");

            if(items.length == 0){
                context.getLogger().info("Ran loot table and received nothing.");
                return "Nothing dropped!";
            }else {
                context.getLogger().info("Ran loot table and received " + items[0].getItem().getName());
                return "Dropped: " + items[0].getItem().getName();
            }
        } else {
            context.getLogger().warn("Player attempted to run loot while not in Dev Mode.");
            return "Invalid command";
        }
    }
}
