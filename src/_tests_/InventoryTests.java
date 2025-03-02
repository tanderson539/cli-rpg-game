package _tests_;

import items.ores.Ore_Copper;
import player.Inventory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTests {

    private final Inventory inv = new Inventory(4);

    @Test
    void testAddItems(){
        inv.addItem(new Ore_Copper());
        assertEquals(4, inv.getInventory().size());
        assertEquals(1, inv.getItem(0).getAmount());
        assertEquals("Copper Ore", inv.getItem(0).getItem().getName());
    }

    @Test
    void testRemoveItems(){
        inv.addItem(new Ore_Copper());
        inv.removeItem(0, 1);
        assertEquals(4, inv.getInventory().size());
        assertNull(inv.getItem(0).getItem());
    }
}
