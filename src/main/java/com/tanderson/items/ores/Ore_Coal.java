package com.tanderson.items.ores;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = "ore_coal")
public class Ore_Coal extends Item implements ItemTableEntry {

    public Ore_Coal() {
        super("Coal Ore", true);
    }
}
