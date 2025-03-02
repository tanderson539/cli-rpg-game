package craftingSystem;

import items.Item;

import java.util.HashMap;

public class CraftingRecipe {
    private HashMap<Item, Integer> recipe;
    private int amountToCraft;

    public CraftingRecipe(int amountToCraft, HashMap<Item, Integer> recipe) {
        this.recipe = recipe;
        this.amountToCraft = amountToCraft;
    }

    public CraftingRecipe(int amountToCraft){
        this.recipe = new HashMap<>();
        this.amountToCraft = amountToCraft;
    }

    public void addIngredient(Item item, int amount){
        this.recipe.put(item, amount);
    }

    public void removeIngredient(Item item){
        this.recipe.remove(item);
    }

    public HashMap<Item, Integer> getRecipe() {
        return recipe;
    }

    public void setRecipe(HashMap<Item, Integer> recipe) {
        this.recipe = recipe;
    }

    public int getAmountToCraft() {
        return amountToCraft;
    }

    public void setAmountToCraft(int amountToCraft) {
        this.amountToCraft = amountToCraft;
    }
}
