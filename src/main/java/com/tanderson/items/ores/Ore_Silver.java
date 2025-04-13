package com.tanderson.items.ores;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = 29L)
public class Ore_Silver extends Item implements ItemTableEntry {
    public Ore_Silver() {
        super(29L, "Silver Ore", true);
    }
}
