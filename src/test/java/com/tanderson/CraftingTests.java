package com.tanderson;

import com.tanderson.craftingSystem.CraftingManager;
import com.tanderson.items.ingots.Ingot_Copper;
import com.tanderson.items.ores.Ore_Coal;
import com.tanderson.items.ores.Ore_Copper;
import com.tanderson.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Crafting Tests")
public class CraftingTests {

    public final Player p = new Player("Test Player");

    @Test
    @DisplayName("can craft a copper ingot")
    void craftCopperIngot() {
        CraftingManager cm = new CraftingManager(p.getInventory());
        p.getInventory().addItem(new Ore_Copper(), 2);
        p.getInventory().addItem(new Ore_Coal(), 1);

        assertTrue(cm.canCraft(new Ingot_Copper().getCraftingRecipe()));

        assertEquals(2, p.getInventory().getAmountOfItem(new Ore_Copper()));
        assertEquals(1, p.getInventory().getAmountOfItem(new Ore_Coal()));
        assertEquals(2, p.getInventory().getUsedInventorySize());

        cm.craftItem(new Ingot_Copper());

        assertEquals(2, p.getInventory().getAmountOfItem(new Ingot_Copper()));
        assertEquals(0, p.getInventory().getAmountOfItem(new Ore_Copper()));
        assertEquals(0, p.getInventory().getAmountOfItem(new Ore_Coal()));
        assertEquals(1, p.getInventory().getUsedInventorySize());
    }
}
