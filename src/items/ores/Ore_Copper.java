package items.ores;

import items.DroppableItem;
import items.Item;

public class Ore_Copper extends Item implements DroppableItem {

    public Ore_Copper(double dropChance, boolean dropsAlways, boolean isEnabled, boolean isUnique) {
        super("Copper Ore", true);
    }
}
