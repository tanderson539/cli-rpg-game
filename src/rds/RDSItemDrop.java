package rds;

import items.Item;
import items.ItemRecord;

/**
 * An object type used to hold methods and attributes associated with Item drops for an RDSTable.
 * Main Additions: Maximum drop amount & Minimum drop amount
 */
public class RDSItemDrop extends RDSObject<ItemTableEntry> {
    private int dropAmountMax;
    private int dropAmountMin;

    public RDSItemDrop(ItemTableEntry item, double probability) {
        super(item, probability);
        this.dropAmountMax = 1;
        this.dropAmountMin = 1;
    }

    public RDSItemDrop(ItemTableEntry item, double probability, int dropAmountMin, int dropAmountMax) {
        super(item, probability);
        this.dropAmountMax = dropAmountMax;
        this.dropAmountMin = dropAmountMin;
    }

    public RDSItemDrop(ItemTableEntry item, double probability, boolean dropsAlways, boolean isUnique, boolean isEnabled){
        super(item, probability, dropsAlways, isUnique, isEnabled);
        this.dropAmountMax = 1;
        this.dropAmountMin = 1;
    }

    public RDSItemDrop(ItemTableEntry item, int dropAmountMin, int dropAmountMax, double probability, boolean dropsAlways, boolean isUnique, boolean isEnabled){
        super(item, probability, dropsAlways, isUnique, isEnabled);
        this.dropAmountMax = dropAmountMax;
        this.dropAmountMin = dropAmountMin;
    }

    public int getDropAmount(RDSRandom rand) {
        if(dropAmountMin == dropAmountMax){
            return dropAmountMax;
        }

        return rand.genInt(dropAmountMin, dropAmountMax);
    }

    public ItemRecord generateItemRecord(RDSRandom rand) {
        return new ItemRecord((Item) this.getAssociatedObject(), this.getDropAmount(rand));
    }

    public int getDropAmountMax() {
        return dropAmountMax;
    }

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
