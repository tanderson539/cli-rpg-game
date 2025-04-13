package com.tanderson.items.ores;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = 26L)
public class Ore_Copper extends Item implements ItemTableEntry {

    public Ore_Copper() {
        super(26L, "Copper Ore", true);
    }
}
