package items;

import util.EnumTypes;

public class ItemRecord {
    private Item item;
    private int amount;

    public ItemRecord(Item item, int amount) {
        this.item = item;

        if(this.item != null && amount <= 0){
            throw new IllegalArgumentException("Item amount must be greater than 0");
        }

        this.amount = amount;
    }

    public ItemRecord(Item item) {
        this.item = item;
        this.amount = 1;
    }

    public Item getItem() {
        return item;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public EnumTypes.ItemRemovalState removeAmount(int amountToRemove) {
        if(this.amount - amountToRemove > 0) {
            this.amount -= amountToRemove;
            return EnumTypes.ItemRemovalState.SUCCESS;
        }else if (this.amount - amountToRemove == 0){
            this.amount -= amountToRemove;
            return EnumTypes.ItemRemovalState.EQUALS_ZERO;
        } else{

            return EnumTypes.ItemRemovalState.FAILURE;
        }
    }

    public boolean isItemNull(){
        return this.item == null;

    }
    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
