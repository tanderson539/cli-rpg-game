package com.tanderson.items.wood.logs;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = "logs_willow")
public class Logs_Willow extends Item implements ItemTableEntry {
    public Logs_Willow() {
        super("Willow Logs", true);
        this.setDescription("A bundle of logs from a willow tree.");
    }
}
