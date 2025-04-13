package com.tanderson.items.wood.logs;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = 12L)
public class Logs_Maple extends Item implements ItemTableEntry {
    public Logs_Maple() {
        super(12L, "Maple Logs", true);
        this.setDescription("A bundle of logs from a large maple tree.");
    }
}
