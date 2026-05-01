package com.tanderson.items.currency;

import com.tanderson.items.Item;
import com.tanderson.items.RegisteredItem;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

@RegisteredItem(id = "currency_gold")
public class Currency_Gold extends Item implements ItemTableEntry {

    public Currency_Gold(){
        super( "Gold", true);
    }
}
