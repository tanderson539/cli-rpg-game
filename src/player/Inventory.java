package player;

import items.Item;

import java.util.ArrayList;

public class Inventory {
    private final int maxSlots;
    private ArrayList<InventorySlot> items;

    public Inventory(int maxSlots) {
        this.maxSlots = maxSlots;
        items = new ArrayList<>(maxSlots);
    }

    public void addItem(Item item) {
        for (InventorySlot slot : items) {
            if (slot.getItem().getName().equals(item.getName()) && item.isStackable()) {
                slot.addAmount(1);
                return;
            }
        }

        if(items.size() < maxSlots) {
            items.add(new InventorySlot(items.size(), item, 1));
        } else {
            System.out.println("Inventory full!");
        }
    }

    public void addItem(Item item, int amount) {
        for (InventorySlot slot : items) {
            if (slot.getItem().getName().equals(item.getName()) && item.isStackable()) {
                slot.addAmount(amount);
                return;
            }
        }

        if(items.size() < maxSlots) {
            items.add(new InventorySlot(items.size(), item, amount));
        } else {
            System.out.println("Inventory full!");
        }
    }

    public void addItem(int index, Item item, int amount) {
        if((index > 0 && index < maxSlots)) {
            if(items.get(index) == null){
                items.add(new InventorySlot(index, item, amount));
            } else {
                System.out.println("Item already present at that index.");
            }
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public void printInventory(){
        for (InventorySlot slot : items) {
            System.out.println(slot.toString());
        }
    }
}
