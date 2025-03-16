package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.items.ItemRecord;
import com.tanderson.systems.rds.tables.OreTable;

public class LootCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        if (context.isDevMode()) {
            OreTable table = new OreTable();

            ItemRecord[] items;

            items = table.runTable();

            if(items.length == 0){
                return "Nothing dropped!";
            }else {
                return "Dropped: " + items[0].getItem().getName();
            }
        } else {
            return "Invalid command";
        }
    }
}
