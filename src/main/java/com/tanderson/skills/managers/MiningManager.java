package com.tanderson.skills.managers;

import com.tanderson.items.Item;
import com.tanderson.items.ItemRecord;
import com.tanderson.items.ores.*;
import com.tanderson.player.Inventory;
import com.tanderson.rds.tables.OreTable;
import com.tanderson.skills.MiningSkill;

import java.util.HashMap;

/**
 * Separates out the logic for performing mining and setting up xp maps away from the player and into
 * its own system.
 */
public class MiningManager {

    private final MiningSkill miningSkill;
    private final Inventory inventory;

    /**
     * oreXpMap uses an item (ore) as a key, with the Integer value being the amount of XP that ore is worth.
     */
    private final HashMap<Item, Integer> oreXpMap;

    /**
     * Default constructor, requires a reference to the player miningSkill and player inventory.
     * Not 100% sure if it is best to keep both of these references in this class,
     * always open to suggestions.
     * @param miningSkill A reference to a player's miningSkill object.
     * @param inventory A reference to a player's inventory object.
     */
    public MiningManager(MiningSkill miningSkill, Inventory inventory) {
        this.miningSkill = miningSkill;
        this.inventory = inventory;
        oreXpMap = this.setupOreXpMap();
    }

    /**
     * Performs a mining action by running the Ore RDS table and selecting an ore to
     * give to the player. Also grants XP to the player based on what ore was selected
     * using the oreXpMap.
     */
    public void mine(){
        OreTable table = new OreTable();

        ItemRecord[] ores = table.runTable();

        for (ItemRecord ore : ores) {
            if (ore != null) {
                if (oreXpMap.containsKey(ore.getItem())) {
                    miningSkill.grantXp(oreXpMap.get(ore.getItem()));
                }

                inventory.addItem(ore.getItem(), ore.getAmount());
            }
        }
    }

    /**
     * Sets up the oreXpMap by manually adding in all the ores that should grant xp, and the xp they should drop.
     * @return A complete hashmap oreXpMap with all ores and xp values added.
     */
    private HashMap<Item, Integer> setupOreXpMap() {
        HashMap<Item, Integer> oreHashMap = new HashMap<>();

        //Item / xp to grant
        oreHashMap.put(new Ore_Copper(), 5);
        oreHashMap.put(new Ore_Iron(), 7);
        oreHashMap.put(new Ore_Coal(), 10);
        oreHashMap.put(new Ore_Silver(), 15);
        oreHashMap.put(new Ore_Gold(), 20);

        return oreHashMap;
    }

    /**
     * Returns the oreXpMap HashMap.
     * @return The oreXpMap HashMap.
     */
    public HashMap<Item, Integer> getOreXpMap() {
        return oreXpMap;
    }
}
