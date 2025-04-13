package com.tanderson.items.wood.logs;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = 11L)
public class Logs_Oak extends Item implements ItemTableEntry {

    public Logs_Oak() {
        super(11L, "Oak Logs", true);
        this.setDescription("A bundle of logs from a large oak tree.");
    }
}
