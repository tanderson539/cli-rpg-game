package player;

import items.Item;
import items.ItemRecord;
import util.EnumTypes;

import java.util.ArrayList;

/**
 * Manages a Player's inventory by offering methods with which to modify that inventory, its slots, and its items.
 */
public class Inventory {
    private int maxSlots;
    private final ArrayList<InventorySlot> items;

    public Inventory(int maxSlots) {
        this.maxSlots = maxSlots;
        items = new ArrayList<>(maxSlots);

        for(int i = 0; i < maxSlots; i++) {
            items.add(new InventorySlot(i, null, 0));
        }
    }

    /**
     * Utilizes the below addItem method to add an item to the player's inventory.
     * @param item The Item object to attempt to place at the specified Index
     */
    public void addItem(Item item) {
        this.addItem(item, 1);
    }

    public void addItem(ItemRecord itemRecord) {
        for (InventorySlot slot : items) {
            if (slot.isItemNull()) {
                continue;
            }else if (slot.getItem().getName().equals(itemRecord.getItem().getName()) && itemRecord.getItem().isStackable()) {
                slot.getItemRecord().addAmount(itemRecord.getAmount());
                return;
            }
        }

        int idxToAdd = this.getNextIndexWithoutItem();

        if(idxToAdd >= 0) {
            items.get(idxToAdd).setItem(itemRecord);
            items.get(idxToAdd).setIndex(idxToAdd);
        } else {
            System.out.println("Inventory full!");
        }
    }

    public void addItem(int index, ItemRecord itemRecord) {
        this.addItem(index, itemRecord.getItem(), itemRecord.getAmount());
    }

    /**
     * Attempts to put a new ItemRecord Object at the next available index with the following parameters.
     * This function creates a new ItemRecord utilizing the item and amount parameters and adds that to the inventory.
     * If an item of the same type already exists, adds the amount parameter to the amount attribute of the ItemRecord at that index.
     * Prints an error message if the player inventory if full.
     * @param item The Item object to attempt to place at the specified Index
     * @param amount The Integer amount of the item to place at the specified index.
     */
    public void addItem(Item item, int amount) {
        ItemRecord itemRecord = new ItemRecord(item, amount);

        this.addItem(itemRecord);
    }

    /**
     * Attempts to put a new ItemRecord Object at the specified index with the following parameters.
     * @param index The Inventory Index to attempt to add the item at.
     * @param item The Item object to attempt to place at the specified Index
     * @param amount The Integer amount of the item to place at the specified index.
     * @see Item
     */
    public void addItem(int index, Item item, int amount) {
        if((index > 0 && index < maxSlots)) {
            if(!items.get(index).isItemNull()){
                items.get(index).setItem(new ItemRecord(item, amount));
                items.get(index).setIndex(index);
            } else {
                System.out.println("Item already present at that index.");
            }
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     *
     * @param index Indicates the index of the item ArrayList to remove an amount of Item from.
     * @param amount Indicates the amount of an Item to remove.
     */
    public void removeItem(int index, int amount) {
        if((index >= 0 && index < maxSlots)) {
            if(items.get(index) == null){
                throw new IndexOutOfBoundsException();
            } else {
                EnumTypes.ItemRemovalState state = items.get(index).getItemRecord().removeAmount(amount);
                if(state == EnumTypes.ItemRemovalState.EQUALS_ZERO) {
                    items.get(index).setItem(new ItemRecord(null, 0));
                } else if (state == EnumTypes.ItemRemovalState.FAILURE ) {
                    System.out.println("You do not have enough " + items.get(index).getItem().getName() + " to do this.");
                }else {
                    System.out.println("it says it succeeded.");
                }
            }
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the index of the next inventory slot that does not contain an item by checking sequentially, starting from index 0.
     * @return the index of the first inventory slot with a null item.
     */
    private int getNextIndexWithoutItem(){
        for(int i = 0; i < maxSlots; i++){
            if(items.get(i).isItemNull()){
                return i;
            }
        }

        return -1;
    }

    /**
     * Performs a print of every item in a player's inventory that is not null.
     * Utilizes the overwritten toString() function of each inventory slot object.
     */
    public void printInventory(){
        for (InventorySlot slot : items) {
            String itemString = slot.toString();
            if (itemString.isEmpty()) {
                continue;
            }else{
                System.out.println(slot);
            }
        }
    }

    public int getUsedInventorySize(){
        int out = 0;

        for(int i = 0; i < maxSlots; i++){
            if(!items.get(i).getItemRecord().isItemNull()){
                out++;
            }
        }

        return out;
    }

    public void removeItem(Item item, int amount){
        for (InventorySlot inventorySlot : items) {
            Item currItem = inventorySlot.getItem();
            if (inventorySlot.isItemNull()) continue;

            if (currItem.equals(item)) {
                EnumTypes.ItemRemovalState state = inventorySlot.getItemRecord().removeAmount(amount);

                if (state == EnumTypes.ItemRemovalState.EQUALS_ZERO) {
                    inventorySlot.setItem(new ItemRecord(null, 0));
                } else if (state == EnumTypes.ItemRemovalState.FAILURE) {
                    System.out.println("You do not have enough " + currItem.getName() + " to do this.");
                }
            }
        }
    }

    public ItemRecord getItemRecord(Item item){
        for(InventorySlot slot : items){
            if(slot.isItemNull()) continue;
            if(slot.getItem().equals(item)){
                return slot.getItemRecord();
            }
        }
        System.out.println("You do not have any " + item.getName() + ".");
        return new ItemRecord(null, 0);
    }

    public int getUnusedInventorySize(){
        return maxSlots - getUsedInventorySize();
    }

    public boolean isInventoryFull(){
        return getUsedInventorySize() == maxSlots;
    }

    public boolean isInventoryEmpty(){
        return getUsedInventorySize() == 0;
    }

    public ArrayList<InventorySlot> getInventory() {
        return items;
    }

    public ItemRecord getItem(int idx) {
        return items.get(idx).getItemRecord();
    }

    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }

    public int getMaxSlots(){
        return maxSlots;
    }
}
