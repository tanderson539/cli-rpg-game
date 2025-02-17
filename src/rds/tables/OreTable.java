package rds.tables;

import items.DroppableItem;
import items.ores.Ore_Coal;
import items.ores.Ore_Copper;
import items.ores.Ore_Iron;
import items.ores.Ore_Silver;
import rds.RDSObject;
import rds.RDSTable;

public class OreTable extends RDSTable {

    public OreTable() {
        super(2);
        RDSObject<DroppableItem> copperOre = new RDSObject<>(new Ore_Copper(), 4.0);
        RDSObject<DroppableItem> ironOre = new RDSObject<>(new Ore_Iron(), 2.5);
        RDSObject<DroppableItem> silverOre = new RDSObject<>(new Ore_Silver(), 2.0);
        RDSObject<DroppableItem> coalOre = new RDSObject<>(new Ore_Coal(), 1.5);

        this.add(copperOre);
        this.add(ironOre);
        this.add(silverOre);
        this.add(coalOre);
    }
}
