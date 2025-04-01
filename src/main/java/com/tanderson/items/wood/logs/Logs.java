package com.tanderson.items.wood.logs;

import com.tanderson.items.Item;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

public class Logs extends Item implements ItemTableEntry {
    public Logs() {
        super("Logs", true);
    }
}
