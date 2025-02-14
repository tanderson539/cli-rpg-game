package player;

import items.Item;

public class InventorySlot {
    private int index;
    private Item item;
    private int amount;

    public InventorySlot(int index, Item item, int amount) {
        this.index = index;
        this.item = item;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "[Slot " + index + "] - " + item.getName() + " x" + amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void removeAmount(int amount) {
        if(this.amount - amount >= 0) {
            this.amount -= amount;
        }else{
            System.out.println("You do not have enough " + item.getName() + " to do this.");
        }
    }

    public int getIndex() {
        return index;
    }
    public Item getItem() {
        return item;
    }
    public int getAmount() {
        return amount;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
