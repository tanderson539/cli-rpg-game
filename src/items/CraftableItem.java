package items;

public interface CraftableItem {
    //implicit attribute requirements:
    //CraftingRecipe recipe;
    //boolean isUnlocked;
    CraftingRecipe getCraftingRecipe();
    boolean isUnlocked();
}
