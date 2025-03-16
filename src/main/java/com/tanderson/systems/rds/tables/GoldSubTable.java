package com.tanderson.systems.rds.tables;

import com.tanderson.items.currency.Currency_Gold;
import com.tanderson.systems.rds.ItemTableEntry;
import com.tanderson.systems.rds.RDSItemDrop;
import com.tanderson.systems.rds.RDSObject;
import com.tanderson.systems.rds.RDSTable;

public class GoldSubTable extends RDSTable {

    public GoldSubTable(){
        super(1);

        RDSObject<ItemTableEntry> gold = new RDSItemDrop(new Currency_Gold(), 1.0, 10, 20);

        this.add(gold);
    }
}
