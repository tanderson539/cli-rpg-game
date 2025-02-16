package items;

public class Item {
    private final String name;
    private final boolean isStackable;

    public Item(String name, boolean isStackable) {
        this.name = name;
        this.isStackable = isStackable;
    }

    public String getName() {
        return name;
    }

    public boolean isStackable() {
        return isStackable;
    }
}
