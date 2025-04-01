package com.tanderson.systems.map.tiles;

import com.tanderson.systems.map.MapTile;
import com.tanderson.systems.map.Point;
import com.tanderson.systems.rds.interfaces.entries.MapTableEntry;

public class GrassTile extends MapTile implements MapTableEntry {

    public GrassTile(Point point) {
        super(point, '"', false);
    }

    public GrassTile() {
        super('"', true);
    }
}
