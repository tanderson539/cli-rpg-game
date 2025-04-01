package com.tanderson.systems.map.tiles;

import com.tanderson.systems.map.MapTile;
import com.tanderson.systems.map.Point;
import com.tanderson.systems.rds.interfaces.entries.MapTableEntry;

public class OreTile extends MapTile implements MapTableEntry {

    public OreTile(Point point) {
        super(point, 'o', true);
    }

    public OreTile() {
        super('o', true);
    }
}
