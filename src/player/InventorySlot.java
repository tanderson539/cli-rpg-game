package player;

import items.Item;
import items.ItemRecord;

public class InventorySlot {
    private int index;
    private ItemRecord item;

    public InventorySlot(int index, Item item, int amount) {
        this.index = index;
        this.item = new ItemRecord(item, amount);
    }

    @Override
    public String toString(){
        if(this.isItemNull()){
            return "";
        }
        return "[Slot " + (this.index + 1) + "] - " + item.getItem().getName() + " x" + item.getAmount();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Item getItem() {
        return this.item.getItem();
    }

    public boolean isItemNull(){
        return this.item.getItem() == null;
    }

    public void setItem(ItemRecord item) {
        this.item = item;
    }

    public int getAmount() {
        return this.item.getAmount();
    }

    public ItemRecord getItemRecord() {
        return this.item;
    }
}
