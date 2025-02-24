package rds.tables;

import items.currency.Currency_Gold;
import rds.ItemTableEntry;
import rds.RDSItemDrop;
import rds.RDSObject;
import rds.RDSTable;

public class GoldSubTable extends RDSTable {

    public GoldSubTable(){
        super(1);

        RDSObject<ItemTableEntry> gold = new RDSItemDrop(new Currency_Gold(), 1.0, 10, 20);

        this.add(gold);
    }
}
