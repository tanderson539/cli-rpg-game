package rds.tables;

import items.DroppableItem;
import items.currency.Currency_Gold;
import rds.RDSObject;
import rds.RDSTable;

public class GoldSubTable extends RDSTable {

    public GoldSubTable(){
        super(1);

        RDSObject<DroppableItem> gold = new RDSObject<>(new Currency_Gold(), 1.0);

        this.add(gold);
    }
}
