package com.tanderson;

import com.tanderson.systems.craftingSystem.CraftingManager;
import com.tanderson.player.Player;
import com.tanderson.systems.rds.RDSRandom;

/**
 * Keeps several critical systems and data types all in one place for injection all over the game.
 * Allows for various classes and systems to easily manipulate game data and perform game functions.
 * NOTICE: There is only one setter method here, for devMode. This class is not intended to modify these components, only allow
 * for manipulation of them and their data. No specific replacement of them.
 * Dev Mode is the only exception, at least for the moment.
 * TODO: Make devMode matter more. Maybe print out additional logs while in dev mode, and allow for inventory manipulation.
 */
public class GameContext {

    private Player player;
    private CraftingManager craftingManager;
    private RDSRandom random;
    private boolean isDevMode;

    /**
     *
     * @param player The player object.
     * @param craftingManager The crafting manager, for allowing crafting of items.
     * @param random An RDSRandon object, allowing for random values to be created anywhere.
     * @param isDevMode A boolean representing if the game is in dev mode or not.
     */
    public GameContext(Player player, CraftingManager craftingManager, RDSRandom random, boolean isDevMode) {
        this.player = player;
        this.craftingManager = craftingManager;
        this.random = random;
        this.isDevMode = isDevMode;
    }

    /**
     * Returns the player object.
     * @return the player object.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the player object.
     * @return the player object.
     */
    public CraftingManager getCraftingManager() {
        return craftingManager;
    }

    /**
     * Returns the RDSRandom object.
     * @return the RDSRandom object.
     */
    public RDSRandom getRandom() {
        return random;
    }

    /**
     * Returns the devMode boolean.
     * @return the devMode boolean.
     */
    public boolean isDevMode() {
        return isDevMode;
    }

    /**
     * Sets if the game is in DevMode or not.
     * @param devMode if the game is in DevMode or not.
     */
    public void setDevMode(boolean devMode) {
        isDevMode = devMode;
    }
}
