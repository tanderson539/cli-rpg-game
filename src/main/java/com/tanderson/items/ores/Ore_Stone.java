package com.tanderson.items.ores;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = 30L)
public class Ore_Stone extends Item implements ItemTableEntry {

    public Ore_Stone() {
        super(30L, "Stone", true);
    }
}
