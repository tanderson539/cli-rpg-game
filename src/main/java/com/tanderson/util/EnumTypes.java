package com.tanderson.util;

/**
 * Serves as a collection of Enums to be used by the game.
 */
public class EnumTypes {
    /**
     * Just a simple enum for the various possible states when removing an item from an inventory.
     */
    public enum ItemRemovalState {SUCCESS, FAILURE, EQUALS_ZERO }

    /**
     * Enum for the various equipment slots.
     */
    public enum WearableItemSlots{HEAD, CHEST, LEGS, BOOT, GLOVES, BACK, NECK, RIGHT_HAND, LEFT_HAND, RING_1, RING_2, BAG}
}
