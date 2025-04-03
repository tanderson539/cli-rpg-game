package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.items.ItemRecord;
import com.tanderson.log.LogLevel;
import com.tanderson.systems.rds.tables.OreItemTable;
import org.apache.commons.lang3.StringUtils;

public class LootCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        if (context.isDevMode()) {
            OreItemTable table;

            if(args.length > 1 && StringUtils.isNumeric(args[1])){
                table = new OreItemTable(Integer.parseInt(args[1]));
                context.getLogger().log("Running loot command with a seed of: " + args[1], LogLevel.INFO);
            } else {
                table = new OreItemTable();
                context.getLogger().log("Running loot command with no explicit seed", LogLevel.INFO);
            }

            ItemRecord[] items;

            items = table.runTable();
            context.getLogger().log("Running loot table...", LogLevel.INFO);

            if(items.length == 0){
                context.getLogger().log("Ran loot table and received nothing.", LogLevel.INFO);
                return "Nothing dropped!";
            }else {
                context.getLogger().log("Ran loot table and received " + items[0].getItem().getName(), LogLevel.INFO);
                return "Dropped: " + items[0].getItem().getName();
            }
        } else {
            context.getLogger().log("Player attempted to run loot while not in Dev Mode.", LogLevel.INFO);
            return "Invalid command";
        }
    }
}
