package com.tanderson.systems.map.tiles;

import com.tanderson.systems.map.MapTile;
import com.tanderson.systems.map.Point;
import com.tanderson.systems.rds.interfaces.entries.MapTableEntry;

public class BlankTile extends MapTile implements MapTableEntry {

    public BlankTile(Point point) {
        super(point, ' ', true);
    }

    public BlankTile() {
        super(' ', true);
    }
}
