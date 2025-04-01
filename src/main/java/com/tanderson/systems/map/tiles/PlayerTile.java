package com.tanderson.systems.map.tiles;

import com.tanderson.systems.map.MapTile;
import com.tanderson.systems.map.Point;

public class PlayerTile extends MapTile {

    public PlayerTile(Point point) {
        super(point, 'p', false);
    }
}
