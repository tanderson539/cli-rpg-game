package com.tanderson.systems.craftingSystem;

import com.tanderson.items.CraftableItem;
import com.tanderson.items.ingots.Ingot_Copper;
import com.tanderson.items.ingots.Ingot_Gold;
import com.tanderson.items.ingots.Ingot_Iron;
import com.tanderson.items.ingots.Ingot_Silver;

import java.util.ArrayList;
import java.util.function.Supplier;

public class CraftingRepo {
    ArrayList<Supplier<CraftableItem>> craftableItemList;

    public CraftingRepo() {
        this.craftableItemList = new ArrayList<>();

        this.craftableItemList.add(Ingot_Copper::new);
        this.craftableItemList.add(Ingot_Iron::new);
        this.craftableItemList.add(Ingot_Silver::new);
        this.craftableItemList.add(Ingot_Gold::new);
    }

    public CraftableItem getCraftableItem(int idx) {
        if(idx < 0 || idx >= this.craftableItemList.size()) return null;
        return craftableItemList.get(idx).get();
    }

    public String printCraftableItemList() {
        StringBuilder out = new StringBuilder("Craftable Items:\n");

        for (int i = 0; i < craftableItemList.size(); i++) {
            out.append(i).append(": ").append(craftableItemList.get(i).get().recipeToString()).append("\n");
        }

        return out.toString();
    }
}
