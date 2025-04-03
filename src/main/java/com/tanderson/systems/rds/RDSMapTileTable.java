package com.tanderson.systems.rds;

import com.tanderson.systems.map.MapTile;
import com.tanderson.systems.rds.interfaces.entries.MapTableEntry;

import java.util.ArrayList;

public class RDSMapTileTable extends RDSTable<MapTableEntry, MapTile> {

    public RDSMapTileTable() {
        super(1);
    }

    public RDSMapTileTable(ArrayList<RDSObject<MapTableEntry>> table) {
        super(table, 1);
    }

    public RDSMapTileTable(int seed) {
        super(1, seed);
    }

    @Override
    public MapTile runTable() {
        if (this.getTable().isEmpty()) {
            return null;
        }

        ArrayList<RDSObject<MapTableEntry>> itemsMarkedAlways = this.getObjectsMarkedAlways();

        if(!itemsMarkedAlways.isEmpty() && itemsMarkedAlways.getFirst().getAssociatedObject() instanceof MapTile) {
            return (MapTile) itemsMarkedAlways.getFirst().getAssociatedObject();
        }

        return getNormalDrop();
    }

    private MapTile getNormalDrop() {
        double randHit = this.getRand().genDouble(this.getTotalProbability());
        double currVal = 0.0;

        for (RDSObject<MapTableEntry> obj : this.getTable()) {
            currVal += obj.getProbability();
            if (currVal > randHit) {
                if (obj.getAssociatedObject() instanceof MapTile) {
                    return (MapTile) obj.getAssociatedObject();
                } else{
                    return null;
                }
            }
        }

        return null;
    }
}
