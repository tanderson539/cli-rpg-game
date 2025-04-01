package com.tanderson.systems.map.tiles;

import com.tanderson.systems.map.MapTile;
import com.tanderson.systems.map.Point;
import com.tanderson.systems.rds.interfaces.entries.MapTableEntry;

public class MountainTile extends MapTile implements MapTableEntry {

    public MountainTile(Point point) {
        super(point, '^', false);
    }

    public MountainTile() {
        super('^', false);
    }
}
