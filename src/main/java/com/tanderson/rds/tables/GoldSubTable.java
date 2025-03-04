package com.tanderson.rds.tables;

import com.tanderson.items.currency.Currency_Gold;
import com.tanderson.rds.ItemTableEntry;
import com.tanderson.rds.RDSItemDrop;
import com.tanderson.rds.RDSObject;
import com.tanderson.rds.RDSTable;

public class GoldSubTable extends RDSTable {

    public GoldSubTable(){
        super(1);

        RDSObject<ItemTableEntry> gold = new RDSItemDrop(new Currency_Gold(), 1.0, 10, 20);

        this.add(gold);
    }
}
