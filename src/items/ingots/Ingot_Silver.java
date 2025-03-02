package items.ingots;

import items.CraftableItem;
import craftingSystem.CraftingRecipe;
import items.ores.Ore_Coal;
import items.ores.Ore_Silver;

public class Ingot_Silver extends CraftableItem {

    public Ingot_Silver() {
        super("Silver Ingot", true, createRecipe());
    }

    private static CraftingRecipe createRecipe() {
        CraftingRecipe recipe = new CraftingRecipe(2);
        recipe.addIngredient(new Ore_Silver(), 2);
        recipe.addIngredient(new Ore_Coal(), 1);

        return recipe;
    }
}
