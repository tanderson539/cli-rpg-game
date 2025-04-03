package com.tanderson;

import com.tanderson.items.ItemRecord;
import com.tanderson.items.ores.Ore_Coal;
import com.tanderson.systems.rds.RDSRandom;
import com.tanderson.systems.rds.tables.OreItemTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RDS Tests")
public class RDSTests {

    @Test
    @DisplayName("can run an ore table and receive a predictable result")
    void testRDSOreTable() {
        OreItemTable oreItemTable = new OreItemTable(1);

        ItemRecord item = oreItemTable.runTable()[0];

        assertEquals("Coal Ore", item.getItem().getName());
        assertEquals(1, item.getAmount());
    }

    @Test
    @DisplayName("verifies RDSRandom")
    void testRDSRandom() {
        RDSRandom rand = new RDSRandom(1);

        assertEquals(5, rand.genInt(10));
        assertEquals(49, rand.genInt(10, 100));
        assertTrue(rand.genDouble() > 0 && rand.genDouble() < 1);
        assertTrue(rand.genDouble(20.0) > 0 && rand.genDouble(20.0) < 20.0);
    }
}
