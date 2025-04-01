package com.tanderson.systems.rds;

import com.tanderson.items.Item;
import com.tanderson.items.ItemRecord;
import com.tanderson.systems.rds.interfaces.entries.ItemTableEntry;

/**
 * An object type used to hold methods and attributes associated with Item drops for an RDSItemTable.
 * Main Additions: Maximum drop amount & Minimum drop amount
 */
public class RDSItemDrop extends RDSObject<ItemTableEntry> {
    private int dropAmountMax;
    private int dropAmountMin;

    /**
     * Constructor to create an object with a defined Item implementing the ItemTableEntry interface and probability.
     * The ItemTableEntry interface has nothing to it besides making it a marker that an item can be added to an RDS table.
     * Reminder that the probability is cumulative with everything in the table. Meaning, a table with 2 items,
     * both with a probability of 1, means both items have a 50% chance of being selected.
     * @param item An Item object implementing the ItemTableEntry interface.
     * @param probability The probability in Double form of the item being selected.
     */
    public RDSItemDrop(ItemTableEntry item, double probability) {
        super(item, probability);
        this.dropAmountMax = 1;
        this.dropAmountMin = 1;
    }

    /**
     * Constructor to create an object using a defined Item implementing the ItemTableEntry interface,
     * a Double probability, and 2 Integers representing the minimum and maximum number of that item to drop.
     * The ItemTableEntry interface has nothing to it besides making it a marker that an item can be added to an RDS table.
     * Reminder that the probability is cumulative with everything in the table. Meaning, a table with 2 items,
     * both with a probability of 1, means both items have a 50% chance of being selected.
     * @param item An Item object implementing the ItemTableEntry interface.
     * @param probability The probability in Double form of the item being selected.
     * @param dropAmountMin Integer minimum of the item to drop.
     * @param dropAmountMax Integer maximum of the item to drop.
     */
    public RDSItemDrop(ItemTableEntry item, double probability, int dropAmountMin, int dropAmountMax) {
        super(item, probability);
        this.dropAmountMax = dropAmountMax;
        this.dropAmountMin = dropAmountMin;
    }

    /**
     * Constructor to create an object using a defined Item implementing the ItemTableEntry interface,
     * a Double probability, and 3 booleans representing if the item should always drop, if the item is unique (can only drop once per table run),
     * and if the item is enabled and can be dropped.
     * The ItemTableEntry interface has nothing to it besides making it a marker that an item can be added to an RDS table.
     * Reminder that the probability is cumulative with everything in the table. Meaning, a table with 2 items,
     * both with a probability of 1, means both items have a 50% chance of being selected.
     * @param item An Item object implementing the ItemTableEntry interface.
     * @param probability The probability in Double form of the item being selected.
     * @param dropsAlways Boolean representing if the item should drop every time the table this object is attached to is run.
     * @param isUnique A boolean representing if this item can only drop a single time each time the table this object is attached to is run.
     * @param isEnabled A boolean representing if this RDSObject is eligible to be dropped by the table it's attached to.
     */
    public RDSItemDrop(ItemTableEntry item, double probability, boolean dropsAlways, boolean isUnique, boolean isEnabled){
        super(item, probability, dropsAlways, isUnique, isEnabled);
        this.dropAmountMax = 1;
        this.dropAmountMin = 1;
    }

    /**
     * Same as the above Constructors, but contains literally all attributes for the underlying RDSObject.
     * The ItemTableEntry interface has nothing to it besides making it a marker that an item can be added to an RDS table.
     * Reminder that the probability is cumulative with everything in the table. Meaning, a table with 2 items,
     * both with a probability of 1, means both items have a 50% chance of being selected.
     * @param item An Item object implementing the ItemTableEntry interface.
     * @param dropAmountMin Integer minimum of the item to drop.
     * @param dropAmountMax Integer maximum of the item to drop.
     * @param probability The probability in Double form of the item being selected.
     * @param dropsAlways Boolean representing if the item should drop every time the table this object is attached to is run.
     * @param isUnique A boolean representing if this item can only drop a single time each time the table this object is attached to is run.
     * @param isEnabled A boolean representing if this RDSObject is eligible to be dropped by the table it's attached to.
     */
    public RDSItemDrop(ItemTableEntry item, int dropAmountMin, int dropAmountMax, double probability, boolean dropsAlways, boolean isUnique, boolean isEnabled){
        super(item, probability, dropsAlways, isUnique, isEnabled);
        this.dropAmountMax = dropAmountMax;
        this.dropAmountMin = dropAmountMin;
    }

    /**
     * Runs RDSRandom to decide how much of an item to drop using the dropAmountMin and dropAmountMax.
     * @param rand An instance of the RDSRandom object for use in determining random numbers.
     * @return An Integer amount of this item to drop to the player.
     */
    private int getDropAmount(RDSRandom rand) {
        if(dropAmountMin == dropAmountMax){
            return dropAmountMax;
        }

        return rand.genInt(dropAmountMin, dropAmountMax);
    }

    /**
     * Creates a new ItemRecord for use when this object is selected from an RDSItemTable. Automatically runs getDropAmount above
     * to determine how much of the item to drop.
     * @param rand An instance of the RDSRandom object for use in determining random numbers.
     * @return An ItemRecord to be dropped to the player.
     */
    public ItemRecord generateItemRecord(RDSRandom rand) {
        return new ItemRecord((Item) this.getAssociatedObject(), this.getDropAmount(rand));
    }

    /**
     * Returns the Integer maximum possible amount of this item that can drop.
     * @return the Integer maximum possible amount of this item that can drop.
     */
    public int getDropAmountMax() {
        return dropAmountMax;
    }

    /**
     * Sets the Integer maximum possible amount of this item that can drop.
     * @param dropAmountMax The Integer maximum possible amount of this item that can drop.
     */
    public void setDropAmountMax(int dropAmountMax) {
        this.dropAmountMax = dropAmountMax;
    }

    public int getDropAmountMin() {
        return dropAmountMin;
    }

    public void setDropAmountMin(int dropAmountMin) {
        this.dropAmountMin = dropAmountMin;
    }
}
