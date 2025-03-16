package com.tanderson.items.currency;

import com.tanderson.items.Item;
import com.tanderson.systems.rds.ItemTableEntry;

public class Currency_Gold extends Item implements ItemTableEntry {

    public Currency_Gold(){
        super("Gold", true);
    }
}
