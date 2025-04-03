package com.tanderson.systems.rds.tables;

import com.tanderson.items.ores.*;
import com.tanderson.systems.rds.*;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

public class OreItemTable extends RDSItemTable {

    public OreItemTable() {
        super(1);
        this.generateTable();
    }

    public OreItemTable(int seed) {
        super(1, seed);
        this.generateTable();
    }

    private void generateTable() {
        RDSObject<ItemTableEntry> copperOre = new RDSItemDrop(new Ore_Copper(), 25.0);
        RDSObject<ItemTableEntry> ironOre = new RDSItemDrop(new Ore_Iron(), 22.5);
        RDSObject<ItemTableEntry> silverOre = new RDSItemDrop(new Ore_Silver(), 20.0);
        RDSObject<ItemTableEntry> coalOre = new RDSItemDrop(new Ore_Coal(), 17.5);
        RDSObject<ItemTableEntry> goldOre = new RDSItemDrop(new Ore_Gold(), 15.0);

        this.add(copperOre);
        this.add(ironOre);
        this.add(silverOre);
        this.add(coalOre);
        this.add(goldOre);
    }
}
