package com.tanderson.systems.rds.tables.map;

import com.tanderson.systems.map.tiles.*;
import com.tanderson.systems.rds.RDSMapTileTable;
import com.tanderson.systems.rds.interfaces.entries.MapTableEntry;
import com.tanderson.systems.rds.RDSObject;

public class MapTileItemTable extends RDSMapTileTable {

    public MapTileItemTable() {
        super();

        RDSObject<MapTableEntry> blankTile = new RDSObject<>(new BlankTile(), 50.0);
        RDSObject<MapTableEntry> grassTile = new RDSObject<>(new GrassTile(), 15.0);
        RDSObject<MapTableEntry> oreTile = new RDSObject<>(new OreTile(), 5.0);
        RDSObject<MapTableEntry> mountainTile = new RDSObject<>(new MountainTile(), 15.0);
        RDSObject<MapTableEntry> waterTile = new RDSObject<>(new WaterTile(), 15.0);

        this.add(blankTile);
        this.add(grassTile);
        this.add(oreTile);
        this.add(mountainTile);
        //this.add(waterTile);
    }
}
