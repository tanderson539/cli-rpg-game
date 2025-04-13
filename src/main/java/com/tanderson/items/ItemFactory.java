package com.tanderson.items;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ItemFactory {

    private static final HashMap<Long, Class<? extends Item>> itemMap = new HashMap<>();

    public static void addItem(Long id, Class<? extends Item> constructor) {
        itemMap.put(id, constructor);
    }

    public static Item createItem(Long id) {
        Class<? extends Item> constructor = itemMap.get(id);

        if (constructor != null) {
            try {
                return constructor.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        throw new IllegalArgumentException("No item registered with ID: " + id);
    }

    public static HashMap<Long, Class<? extends Item>> getItemMap() {
        return itemMap;
    }
}
