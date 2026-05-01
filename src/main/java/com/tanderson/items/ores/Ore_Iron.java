package com.tanderson.items.ores;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = "ore_iron")
public class Ore_Iron extends Item implements ItemTableEntry {

    public Ore_Iron() {
        super( "Iron Ore", true);
    }
}
