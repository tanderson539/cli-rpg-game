package com.tanderson.items;

public abstract class Item {
    private final String name;
    private final Long id;
    private final boolean isStackable;
    private String description;

    public Item(Long id, String name, boolean isStackable) {
        this.id = id;
        this.name = name;
        this.isStackable = isStackable;
    }

    public String getName() {
        return name;
    }

    public boolean isStackable() {
        return isStackable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
