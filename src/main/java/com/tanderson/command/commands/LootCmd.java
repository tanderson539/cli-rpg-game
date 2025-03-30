package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.items.ItemRecord;
import com.tanderson.log.LogLevel;
import com.tanderson.systems.rds.tables.OreTable;

public class LootCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        if (context.isDevMode()) {
            OreTable table = new OreTable();

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
