package com.tanderson;

import com.tanderson.items.ores.Ore_Copper;
import com.tanderson.player.Inventory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Inventory Tests")
public class InventoryTests {

    private final Inventory inv = new Inventory(4);

    @Test
    @DisplayName("can add items to a player inventory.")
    void testAddItems(){
        inv.addItem(new Ore_Copper());
        assertEquals(4, inv.getInventory().size());
        assertEquals(1, inv.getItem(0).getAmount());
        assertEquals("Copper Ore", inv.getItem(0).getItem().getName());
    }

    @Test
    @DisplayName("can remove items from a player inventory.")
    void testRemoveItems(){
        inv.addItem(new Ore_Copper());
        assertEquals(1, inv.getItem(0).getAmount());
        assertEquals("Copper Ore", inv.getItem(0).getItem().getName());
        inv.removeItem(0, 1);
        assertEquals(4, inv.getInventory().size());
        assertNull(inv.getItem(0).getItem());

        inv.addItem(new Ore_Copper());
        assertEquals(1, inv.getItem(0).getAmount());
        assertEquals("Copper Ore", inv.getItem(0).getItem().getName());
        inv.removeItem(new Ore_Copper(), 1);
        assertEquals(4, inv.getInventory().size());
        assertNull(inv.getItem(0).getItem());
    }

}
