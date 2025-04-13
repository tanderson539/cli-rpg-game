package com.tanderson.items.ores;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = 28L)
public class Ore_Iron extends Item implements ItemTableEntry {

    public Ore_Iron() {
        super(28L, "Iron Ore", true);
    }
}
