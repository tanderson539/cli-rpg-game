package com.tanderson.items.wood.logs;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = 13L)
public class Logs_Willow extends Item implements ItemTableEntry {
    public Logs_Willow() {
        super(13L, "Willow Logs", true);
        this.setDescription("A bundle of logs from a willow tree.");
    }
}
