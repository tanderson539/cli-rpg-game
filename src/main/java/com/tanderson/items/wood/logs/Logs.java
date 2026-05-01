package com.tanderson.items.wood.logs;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = "logs")
public class Logs extends Item implements ItemTableEntry {
    public Logs() {
        super( "Logs", true);
        this.setDescription("A bundle of logs from a tree.");
    }
}
