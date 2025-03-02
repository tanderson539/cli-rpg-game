package craftingSystem;

import items.CraftableItem;
import items.ingots.Ingot_Copper;
import items.ingots.Ingot_Gold;
import items.ingots.Ingot_Iron;
import items.ingots.Ingot_Silver;

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
        return craftableItemList.get(idx);
    }

    public void printCraftableItemList() {
        System.out.println("Craftable Items:");

        for (int i = 0; i < craftableItemList.size(); i++) {
            System.out.println(i + ": " + craftableItemList.get(i).getName());
        }
    }
}
