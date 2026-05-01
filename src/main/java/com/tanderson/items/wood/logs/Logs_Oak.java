package com.tanderson.items.wood.logs;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = "logs_oak")
public class Logs_Oak extends Item implements ItemTableEntry {

    public Logs_Oak() {
        super( "Oak Logs", true);
        this.setDescription("A bundle of logs from a large oak tree.");
    }
}
