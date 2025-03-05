package com.tanderson.rds.tables;

import com.tanderson.items.ores.*;
import com.tanderson.rds.*;

public class OreTable extends RDSTable {

    public OreTable() {
        super(1);
        RDSObject<ItemTableEntry> copperOre = new RDSItemDrop(new Ore_Copper(), 4.0);
        RDSObject<ItemTableEntry> ironOre = new RDSItemDrop(new Ore_Iron(), 2.5);
        RDSObject<ItemTableEntry> silverOre = new RDSItemDrop(new Ore_Silver(), 2.0);
        RDSObject<ItemTableEntry> coalOre = new RDSItemDrop(new Ore_Coal(), 1.5);
        RDSObject<ItemTableEntry> goldOre = new RDSItemDrop(new Ore_Gold(), 0.5);
        RDSObject<ItemTableEntry> nullDrop = new RDSObject<>(null, 1.0);

        //RDSObject<SubTableTableEntry> goldSubTable = new RDSObject<>(new GoldSubTable(), 0.25, false, true, true);

        this.add(copperOre);
        this.add(ironOre);
        this.add(silverOre);
        this.add(coalOre);
        this.add(goldOre);
        //this.add(goldSubTable);
        //this.add(nullDrop);
    }
}
