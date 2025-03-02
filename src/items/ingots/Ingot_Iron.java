package items.ingots;

import items.CraftableItem;
import craftingSystem.CraftingRecipe;
import items.ores.Ore_Coal;
import items.ores.Ore_Iron;

public class Ingot_Iron extends CraftableItem {

    public Ingot_Iron() {
        super("Iron Ingot", true, createRecipe());
    }

    private static CraftingRecipe createRecipe() {
        CraftingRecipe recipe = new CraftingRecipe(2);
        recipe.addIngredient(new Ore_Iron(), 2);
        recipe.addIngredient(new Ore_Coal(), 1);

        return recipe;
    }
}
