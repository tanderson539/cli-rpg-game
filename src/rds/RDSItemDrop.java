package rds;

import items.DroppableItem;

public class RDSItemDrop extends RDSObject<DroppableItem> {
    private int dropAmountMax;
    private int dropAmountMin;

    public RDSItemDrop(DroppableItem item, double probability) {
        super(item, probability);
        this.dropAmountMax = 1;
        this.dropAmountMin = 1;
    }

    public RDSItemDrop(DroppableItem item, double probability, boolean dropsAlways, boolean isUnique, boolean isEnabled){
        super(item, probability, dropsAlways, isUnique, isEnabled);
        this.dropAmountMax = 1;
        this.dropAmountMin = 1;
    }

    public RDSItemDrop(DroppableItem item, int dropAmountMin, int dropAmountMax, double probability, boolean dropsAlways, boolean isUnique, boolean isEnabled){
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
