package player;

import items.Item;
import items.ItemRecord;
import util.EnumTypes;

import java.util.ArrayList;

/**
 * Manages a Player's inventory by offering methods with which to modify that inventory, its slots, and its items.
 */
public class Inventory {
    private final int maxSlots;
    private ArrayList<InventorySlot> items;

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

    /**
     * Attempts to put a new ItemRecord Object at the next available index with the following parameters.
     * This function creates a new ItemRecord utilizing the item and amount parameters and adds that to the inventory.
     * If an item of the same type already exists, adds the amount parameter to the amount attribute of the ItemRecord at that index.
     * Prints an error message if the player inventory if full.
     * @param item The Item object to attempt to place at the specified Index
     * @param amount The Integer amount of the item to place at the specified index.
     */
    public void addItem(Item item, int amount) {
        for (InventorySlot slot : items) {
            if (slot.isItemNull()) {
                continue;
            }else if (slot.getItem().getName().equals(item.getName()) && item.isStackable()) {
                slot.getItemRecord().addAmount(amount);
                return;
            }
        }

        int idxToAdd = this.getNextIndexWithoutItem();

        if(idxToAdd >= 0) {
            System.out.println("adding new item to inventory at index " + idxToAdd);
            items.get(idxToAdd).setItem(new ItemRecord(item, amount));
            items.get(idxToAdd).setIndex(idxToAdd);
        } else {
            System.out.println("Inventory full!");
        }
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
}
