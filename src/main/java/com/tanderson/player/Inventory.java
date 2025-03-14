package com.tanderson.player;

import com.tanderson.display.TextAreaAppender;
import com.tanderson.items.Item;
import com.tanderson.items.ItemRecord;
import com.tanderson.util.EnumTypes;

import java.util.ArrayList;

/**
 * Manages a Player's inventory by offering methods with which to modify that inventory, its slots, and its items.
 */
public class Inventory {
    private int maxSlots;
    private final ArrayList<InventorySlot> inventorySlots;

    /**
     * Instantiates the inventorySlots ArrayList, then fills the ArrayList with InventorySlot objects containing a null
     * ItemRecord. Essentially, creating an empty inventory to be manipulated.
     * @param maxSlots The integer to set the maximum player inventory size to.
     */
    public Inventory(int maxSlots) {
        this.maxSlots = maxSlots;
        inventorySlots = new ArrayList<>(maxSlots);

        for(int i = 0; i < maxSlots; i++) {
            inventorySlots.add(new InventorySlot(i, null, 0));
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
     * Adds an ItemRecord to a player's inventory directly.
     * Checks if an item already exists in a player inventory, and if it does, adds to that amount with the amount from the ItemRecord
     * If the inventory is not full, then adds the item to the next available empty inventory slot.
     * @param itemRecord The ItemRecord object to add to the player inventory.
     */
    public void addItem(ItemRecord itemRecord) {

        for (InventorySlot slot : inventorySlots) {
            if (!slot.isItemNull() && slot.getItem().getName().equals(itemRecord.getItem().getName()) && itemRecord.getItem().isStackable()) {
                slot.getItemRecord().addAmount(itemRecord.getAmount());
                return;
            }
        }

        int idxToAdd = this.getNextIndexWithoutItem();

        if (idxToAdd == -1){
            TextAreaAppender.appendln("Inventory full!");
            return;
        }

        if(itemRecord.getItem().isStackable()) {
            inventorySlots.get(idxToAdd).setItemRecord(itemRecord);
            inventorySlots.get(idxToAdd).setIndex(idxToAdd);
        } else {
            this.addNonStackableItem(itemRecord.getItem(), itemRecord.getAmount());
        }
    }

    /**
     *
     * @param index The Inventory Index to attempt to add the item at.
     * @param itemRecord The item record to attempt to add to the inventory. Uses the Item object and amount from the record
     *                   to determine what to add.
     */
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
        if (!item.isStackable() && amount != 1) throw new IllegalArgumentException("Amount must be 1 for non-stackable items");

        if(index < 0 || index > maxSlots) throw new IndexOutOfBoundsException();

        if(inventorySlots.get(index).isItemNull()){
            inventorySlots.get(index).setItemRecord(new ItemRecord(item, amount));
            inventorySlots.get(index).setIndex(index);
        } else {
            TextAreaAppender.appendln("Item already present at that index.");
        }
    }

    /**
     * Removes an item from a player's inventory based on the item index and an amount.
     * @param index Indicates the index of the item ArrayList to remove an amount of Item from.
     * @param amount Indicates the amount of an Item to remove.
     */
    public void removeItem(int index, int amount) {
        if((index >= 0 && index < maxSlots)) {
            if(inventorySlots.get(index).getItem() == null){
                TextAreaAppender.appendln("No item found at the index!");
            } else {
                EnumTypes.ItemRemovalState state = inventorySlots.get(index).getItemRecord().removeAmount(amount);
                if(state == EnumTypes.ItemRemovalState.EQUALS_ZERO) {
                    inventorySlots.get(index).setItemRecord(new ItemRecord(null, 0));
                } else if (state == EnumTypes.ItemRemovalState.FAILURE ) {
                    TextAreaAppender.appendln("You do not have enough " + inventorySlots.get(index).getItem().getName() + " to do this.");
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
            if(inventorySlots.get(i).isItemNull()){
                return i;
            }
        }

        return -1;
    }

    /**
     * Adds an integer amount of an unstackable item to the player's inventory.
     * @param item The item to add to the player's inventory.
     * @param amount The amount of individual items above to add to the player's inventory.
     */
    private void addNonStackableItem(Item item, int amount){

        for (int i = 0; i < amount; i++) {
            int idxToAdd = this.getNextIndexWithoutItem();

            if (idxToAdd == -1){
                TextAreaAppender.appendln("Inventory full!");
                break;
            }

            inventorySlots.get(idxToAdd).setItemRecord(new ItemRecord(item, 1));
            inventorySlots.get(idxToAdd).setIndex(idxToAdd);
        }
    }

    /**
     * Performs a print of every item in a player's inventory that is not null.
     * Utilizes the overwritten toString() function of each inventory slot object.
     */
    public String printInventory(){
        StringBuilder sb = new StringBuilder();
        for (InventorySlot slot : inventorySlots) {
            String itemString = slot.toString();
            if (!itemString.isEmpty()) {
                sb.append(itemString).append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Gets the number of inventory spaces in a player inventory that contains an item. Checks each player inventory slot
     * and adds 1 to the output variable if the itemRecord in that slot is not null.
     * @return Integer amount of inventory spaces occupied by an item.
     */
    public int getUsedInventorySize(){
        int out = 0;

        for(int i = 0; i < maxSlots; i++){
            if(!inventorySlots.get(i).getItemRecord().isItemNull()){
                out++;
            }
        }

        return out;
    }

    /**
     * A more general removeItem function that, instead of inputting an index and amount, utilizes an Item object to remove.
     * @param item Specifies which Item type to attempt to remove from the player inventory.
     * @param amount Specifies the amount of the above Item to attempt to remove from the player inventory.
     */
    public void removeItem(Item item, int amount){

        if(!item.isStackable() && amount != 1){
            this.removeUnstackableItemFromInventory(item, amount);
        } else {
            this.removeStackableItemFromInventory(item, amount);
        }

    }

    /**
     * Attempts to remove an integer amount of an unstackable item from the player's inventory.
     * @param item The item to attempt to remove.
     * @param amount The amount of that item to attempt to remove.
     */
    private void removeUnstackableItemFromInventory(Item item, int amount){
        int amtToRemove = amount;
        if (this.getAmountOfItem(item) < amount) {
            TextAreaAppender.appendln("You do not have enough " + item.getName() + " to do this.");
            return;
        }

        for (InventorySlot inventorySlot : inventorySlots) {
            if(amtToRemove <= 0) break;

            if (inventorySlot.isItemNull()) continue;

            if(inventorySlot.getItem().equals(item)){
                EnumTypes.ItemRemovalState state = inventorySlot.getItemRecord().removeAmount(1);

                if (state == EnumTypes.ItemRemovalState.EQUALS_ZERO) {
                    amtToRemove--;
                } else if (state == EnumTypes.ItemRemovalState.FAILURE) {
                    TextAreaAppender.appendln("You do not have enough " + item.getName() + " to do this.");
                } else if (state == EnumTypes.ItemRemovalState.SUCCESS) {
                    TextAreaAppender.appendln("something weird happened cause apparently there was more than 1 of this unstackable item?");
                }
            }
        }
    }

    /**
     * Attempts to remove an integer amount of a stackable item from the player's inventory.
     * @param item The item to attempt to remove.
     * @param amount The amount of that item to attempt to remove.
     */
    private void removeStackableItemFromInventory(Item item, int amount){
        for (InventorySlot inventorySlot : inventorySlots) {
            Item currItem = inventorySlot.getItem();
            if (inventorySlot.isItemNull()) continue;

            if (currItem.equals(item)) {
                EnumTypes.ItemRemovalState state = inventorySlot.getItemRecord().removeAmount(amount);

                if (state == EnumTypes.ItemRemovalState.EQUALS_ZERO) {
                    inventorySlot.setItemRecord(new ItemRecord(null, 0));
                } else if (state == EnumTypes.ItemRemovalState.FAILURE) {
                    TextAreaAppender.appendln("You do not have enough " + item.getName() + " to do this.");
                }
                break;
            }
        }
    }

    /**
     * Iterates through the player Inventory and, if the Item parameter exists in the player inventory,
     * returns the ItemRecord of that Item.
     * @param item Specifies an Item type to return the Record for
     * @return Returns an ItemRecord from
     */
    public ItemRecord getItemRecord(Item item){
        for(InventorySlot slot : inventorySlots){
            if(slot.isItemNull()) continue;
            if(slot.getItem().equals(item)){
                return slot.getItemRecord();
            }
        }
        TextAreaAppender.appendln("You do not have any " + item.getName() + ".");
        return new ItemRecord(null, 0);
    }

    /**
     * Gets the number of spaces in a player inventory that does not contain an Item. In other words,
     * returns the amount of InventorySlots in a player inventory who's corresponding Item is null.
     * @return The number of spaces in a player inventory that does not contain an Item.
     */
    public int getUnusedInventorySize(){
        return maxSlots - getUsedInventorySize();
    }

    /**
     * Returns a boolean representing if the player inventory is full or not.
     * @return A boolean representing if the player inventory is full or not.
     */
    public boolean isInventoryFull(){
        return getUsedInventorySize() == maxSlots;
    }

    /**
     * Returns a boolean representing if the player inventory is empty or not.
     * @return a boolean representing if the player inventory is empty or not.
     */
    public boolean isInventoryEmpty(){
        return getUsedInventorySize() == 0;
    }

    /**
     * Searches a player's inventory for an item and returns an integer representing the amount of that item.
     * @param item An Item object to search for
     * @return An integer representing the amount of that item in the player's inventory.
     */
    public int getAmountOfItem(Item item){
        if(item.isStackable()){
            return this.getItemRecord(item).getAmount();
        }else{
            return this.getAmountOfUnstackableItem(item);
        }
    }

    /**
     * Searches a player's inventory for an item assumed to be unstackable and returns an integer representing the amount of that item.
     * @param item An Item object to search for
     * @return An integer representing the amount of that item in the player's inventory.
     */
    private int getAmountOfUnstackableItem(Item item){
        if (item.isStackable()) throw new IllegalArgumentException("Stackable item given to when unstackable item was expected");

        int amount = 0;

        for(InventorySlot slot : inventorySlots){
            if(slot.isItemNull()) continue;

            if(slot.getItem().equals(item)) amount++;
        }
        return amount;
    }

    /**
     * Returns an ArrayList containing the player inventory.
     * @return An ArrayList containing the player inventory.
     */
    public ArrayList<InventorySlot> getInventory() {
        return inventorySlots;
    }

    /**
     * Returns an ItemRecord of whatever is in the player inventory at a specific index (even null).
     * @param idx Integer index to return the ItemRecord with.
     * @return An ItemRecord from the player inventory at a specific index.
     */
    public ItemRecord getItem(int idx) {
        return inventorySlots.get(idx).getItemRecord();
    }

    /**
     * Sets the maximum player inventory size.
     * @param maxSlots The integer to set the maximum player inventory size to.
     */
    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }

    /**
     * Returns the maximum player inventory size
     * @return The integer value for the player's maximum inventory size.
     */
    public int getMaxSlots(){
        return maxSlots;
    }
}
