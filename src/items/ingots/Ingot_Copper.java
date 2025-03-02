package items.ingots;

import items.CraftableItem;
import craftingSystem.CraftingRecipe;
import items.ores.Ore_Coal;
import items.ores.Ore_Copper;
import rds.ItemTableEntry;

public class Ingot_Copper extends CraftableItem implements ItemTableEntry {
    public Ingot_Copper() {
        super("Copper Ingot", true, createRecipe());
    }

    private static CraftingRecipe createRecipe() {
        CraftingRecipe recipe = new CraftingRecipe(2);
        recipe.addIngredient(new Ore_Copper(), 2);
        recipe.addIngredient(new Ore_Coal(), 1);

        return recipe;
    }
}
