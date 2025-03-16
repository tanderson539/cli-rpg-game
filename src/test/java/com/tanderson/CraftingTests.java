package com.tanderson;

import com.tanderson.items.ingots.Ingot_Gold;
import com.tanderson.items.ingots.Ingot_Iron;
import com.tanderson.items.ingots.Ingot_Silver;
import com.tanderson.items.ores.*;
import com.tanderson.items.tools.Tool_Hammer_Stone;
import com.tanderson.items.wood.logs.TreeBranch;
import com.tanderson.systems.craftingSystem.CraftingManager;
import com.tanderson.items.ingots.Ingot_Copper;
import com.tanderson.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Crafting Tests")
public class CraftingTests {

    public Player p;

    public CraftingManager cm;

    @BeforeEach
    void setup() {
        this.p = new Player("Test Player");
        this.cm = new CraftingManager(p.getInventory());
    }

    @Test
    @DisplayName("can craft a copper ingot")
    void craftCopperIngot() {
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

    @Test
    @DisplayName("can craft iron ingot")
    void craftIronIngot() {
        p.getInventory().addItem(new Ore_Iron(), 2);
        p.getInventory().addItem(new Ore_Coal(), 1);

        assertTrue(cm.canCraft(new Ingot_Iron().getCraftingRecipe()));

        assertEquals(2, p.getInventory().getAmountOfItem(new Ore_Iron()));
        assertEquals(1, p.getInventory().getAmountOfItem(new Ore_Coal()));
        assertEquals(2, p.getInventory().getUsedInventorySize());

        cm.craftItem(new Ingot_Iron());

        assertEquals(2, p.getInventory().getAmountOfItem(new Ingot_Iron()));
        assertEquals(0, p.getInventory().getAmountOfItem(new Ore_Iron()));
        assertEquals(0, p.getInventory().getAmountOfItem(new Ore_Coal()));
        assertEquals(1, p.getInventory().getUsedInventorySize());
    }

    @Test
    @DisplayName("can craft silver ingot")
    void craftSilverIngot() {
        p.getInventory().addItem(new Ore_Silver(), 2);
        p.getInventory().addItem(new Ore_Coal(), 1);

        assertTrue(cm.canCraft(new Ingot_Silver().getCraftingRecipe()));

        assertEquals(2, p.getInventory().getAmountOfItem(new Ore_Silver()));
        assertEquals(1, p.getInventory().getAmountOfItem(new Ore_Coal()));
        assertEquals(2, p.getInventory().getUsedInventorySize());

        cm.craftItem(new Ingot_Silver());

        assertEquals(2, p.getInventory().getAmountOfItem(new Ingot_Silver()));
        assertEquals(0, p.getInventory().getAmountOfItem(new Ore_Silver()));
        assertEquals(0, p.getInventory().getAmountOfItem(new Ore_Coal()));
        assertEquals(1, p.getInventory().getUsedInventorySize());
    }

    @Test
    @DisplayName("can craft gold ingot")
    void craftGoldIngot() {
        p.getInventory().addItem(new Ore_Gold(), 2);
        p.getInventory().addItem(new Ore_Coal(), 1);

        assertTrue(cm.canCraft(new Ingot_Gold().getCraftingRecipe()));

        assertEquals(2, p.getInventory().getAmountOfItem(new Ore_Gold()));
        assertEquals(1, p.getInventory().getAmountOfItem(new Ore_Coal()));
        assertEquals(2, p.getInventory().getUsedInventorySize());

        cm.craftItem(new Ingot_Gold());

        assertEquals(2, p.getInventory().getAmountOfItem(new Ingot_Gold()));
        assertEquals(0, p.getInventory().getAmountOfItem(new Ore_Gold()));
        assertEquals(0, p.getInventory().getAmountOfItem(new Ore_Coal()));
        assertEquals(1, p.getInventory().getUsedInventorySize());
    }

    @Test
    @DisplayName("Can craft stone hammer")
    void craftStoneHammer() {
        p.getInventory().addItem(new Ore_Stone(), 1);
        p.getInventory().addItem(new TreeBranch(), 2);

        assertTrue(cm.canCraft(new Tool_Hammer_Stone().getCraftingRecipe()));

        assertEquals(2, p.getInventory().getAmountOfItem(new TreeBranch()));
        assertEquals(1, p.getInventory().getAmountOfItem(new Ore_Stone()));
        assertEquals(2, p.getInventory().getUsedInventorySize());

        cm.craftItem(new Tool_Hammer_Stone());

        assertEquals(2, p.getInventory().getAmountOfItem(new Tool_Hammer_Stone()));
        assertEquals(0, p.getInventory().getAmountOfItem(new Ore_Stone()));
        assertEquals(0, p.getInventory().getAmountOfItem(new TreeBranch()));
        assertEquals(1, p.getInventory().getUsedInventorySize());
    }
}
