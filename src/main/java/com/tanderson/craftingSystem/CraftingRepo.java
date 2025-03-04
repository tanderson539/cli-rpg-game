package com.tanderson.craftingSystem;

import com.tanderson.items.CraftableItem;
import com.tanderson.items.ingots.Ingot_Copper;
import com.tanderson.items.ingots.Ingot_Gold;
import com.tanderson.items.ingots.Ingot_Iron;
import com.tanderson.items.ingots.Ingot_Silver;

import java.util.ArrayList;

public class CraftingRepo {
    ArrayList<CraftableItem> craftableItemList;

    public CraftingRepo() {
        this.craftableItemList = new ArrayList<>();

        this.craftableItemList.add(new Ingot_Copper());
        this.craftableItemList.add(new Ingot_Iron());
        this.craftableItemList.add(new Ingot_Silver());
        this.craftableItemList.add(new Ingot_Gold());
    }

    public CraftableItem getCraftableItem(int idx) {
        if(idx < 0 || idx >= this.craftableItemList.size()) return null;
        return craftableItemList.get(idx);
    }

    public void printCraftableItemList() {
        System.out.println("Craftable Items:");

        for (int i = 0; i < craftableItemList.size(); i++) {
            System.out.println(i + ": " + craftableItemList.get(i).recipeToString());
        }
    }
}
