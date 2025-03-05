package com.tanderson.skills.managers;

import com.tanderson.items.Item;
import com.tanderson.items.ItemRecord;
import com.tanderson.items.ores.*;
import com.tanderson.player.Inventory;
import com.tanderson.rds.tables.OreTable;
import com.tanderson.skills.MiningSkill;

import java.util.HashMap;

public class MiningManager {

    private final MiningSkill miningSkill;
    private final Inventory inventory;

    private final HashMap<Item, Integer> oreXpMap;

    public MiningManager(MiningSkill miningSkill, Inventory inventory) {
        this.miningSkill = miningSkill;
        this.inventory = inventory;
        oreXpMap = this.setupOreXpMap();
    }

    public void mine(){
        OreTable table = new OreTable();

        ItemRecord[] ores = table.runTable();

        for (ItemRecord ore : ores) {
            if (ore != null) {
                miningSkill.grantXp(oreXpMap.get(ore.getItem()));
                inventory.addItem(ore.getItem(), ore.getAmount());
            }
        }
    }

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

    public HashMap<Item, Integer> getOreXpMap() {
        return oreXpMap;
    }
}
