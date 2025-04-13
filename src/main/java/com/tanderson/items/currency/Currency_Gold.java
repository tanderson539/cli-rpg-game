package com.tanderson.items.currency;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = 1L)
public class Currency_Gold extends Item implements ItemTableEntry {

    public Currency_Gold(){
        super(1L, "Gold", true);
    }
}
