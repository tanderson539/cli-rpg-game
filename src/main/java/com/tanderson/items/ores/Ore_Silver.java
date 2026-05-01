package com.tanderson.items.ores;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = "ore_silver")
public class Ore_Silver extends Item implements ItemTableEntry {
    public Ore_Silver() {
        super( "Silver Ore", true);
    }
}
