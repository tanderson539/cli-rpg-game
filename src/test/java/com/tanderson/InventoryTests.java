package com.tanderson;

import com.tanderson.items.ores.Ore_Copper;
import com.tanderson.items.ores.Ore_Gold;
import com.tanderson.player.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Inventory Tests")
public class InventoryTests {

    private final Inventory inv = new Inventory(4);

    @BeforeEach
    void setUp(){
        inv.addItem(new Ore_Copper());
        inv.addItem(2, new Ore_Gold(), 12);
    }

    @Test
    @DisplayName("can add items to a player inventory.")
    void testAddItems(){

        assertEquals(4, inv.getInventory().size());
        assertEquals(2, inv.getUsedInventorySize());
        assertEquals(1, inv.getItem(0).getAmount());
        assertEquals("Copper Ore", inv.getItem(0).getItem().getName());

        assertEquals("Gold Ore", inv.getItem(2).getItem().getName());
        assertEquals(12, inv.getItem(2).getAmount());
    }

    @Test
    @DisplayName("can remove items from a player inventory.")
    void testRemoveItems(){
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
