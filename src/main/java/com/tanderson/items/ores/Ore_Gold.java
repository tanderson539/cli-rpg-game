package com.tanderson.items.ores;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = 27L)
public class Ore_Gold extends Item implements ItemTableEntry {

    public Ore_Gold() {
        super(27L, "Gold Ore", true);
    }
}
