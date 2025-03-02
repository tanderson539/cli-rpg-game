package items;

import craftingSystem.CraftingRecipe;

public class CraftableItem extends Item{

    private CraftingRecipe recipe;

    public CraftableItem(String name, boolean isStackable, CraftingRecipe recipe){
        super(name, isStackable);
        this.recipe = recipe;
    }


    public CraftingRecipe getCraftingRecipe() {
        return recipe;
    }

    public void setCraftingRecipe(CraftingRecipe recipe) {
        this.recipe = recipe;
    }

}
