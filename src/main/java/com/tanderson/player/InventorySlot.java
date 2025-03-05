package com.tanderson.player;

import com.tanderson.items.Item;
import com.tanderson.items.ItemRecord;

/**
 * InventorySlots hold an ItemRecord (Item, Integer amount) and an index representing the Slots index in the
 * inventory ArrayList.
 */
public class InventorySlot {
    private int index;
    private ItemRecord item;

    /**
     *
     * @param index The InventorySlots index in the Inventory ArrayList.
     * @param item The specific Item to be placed into the Slot.
     * @param amount The amount of the Item above in the slot.
     */
    public InventorySlot(int index, Item item, int amount) {
        this.index = index;
        this.item = new ItemRecord(item, amount);
    }

    /**
     *
     * @param index The InventorySlots index in the Inventory ArrayList.
     * @param itemRecord The ItemRecord object to place into the slot.
     */
    public InventorySlot(int index, ItemRecord itemRecord){
        this.index = index;
        this.item = itemRecord;
    }

    /**
     * Creates and returns a String representation of the InventorySlot for use in printing.
     * @return The string representation of the InventorySlot.
     */
    @Override
    public String toString(){
        if(this.isItemNull()){
            return "";
        }

        //if it is stackable
        if(this.item.getItem().isStackable()){
            return "[Slot " + (this.index + 1) + "] - " + item.getItem().getName() + " x" + item.getAmount();
        }

        // Unstackable return
        return "[Slot " + (this.index + 1) + "] - " + item.getItem().getName();
    }

    /**
     * Returns the index of a particular InventorySlot.
     * @return The index of a particular InventorySlot.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Resets the index of a particular InventorySlot.
     * @param index Resets the index of a particular InventorySlot.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Returns the Item object associated with a particular InventorySlot.
     * @return The Item object associated with a particular InventorySlot.
     */
    public Item getItem() {
        return this.item.getItem();
    }

    /**
     * For use when you need to know if an Item in an InventorySlot is null.
     * @return A boolean representing if the ItemRecord of an InventorySlot is null.
     */
    public boolean isItemNull(){
        return this.item.getItem() == null;
    }

    /**
     * Resets an ItemRecord object in an InventorySlot.
     * @param item The specific ItemRecord object to replace with.
     */
    public void setItemRecord(ItemRecord item) {
        this.item = item;
    }

    /**
     * Returns the amount of an Item within an InventorySlot.
     * @return The amount of an Item within an InventorySlot.
     */
    public int getAmount() {
        return this.item.getAmount();
    }

    /**
     * Returns the ItemRecord object within a particular InventorySlot.
     * @return The ItemRecord object within a particular InventorySlot.
     */
    public ItemRecord getItemRecord() {
        return this.item;
    }
}
