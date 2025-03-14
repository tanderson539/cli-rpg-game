package com.tanderson;

import com.tanderson.craftingSystem.CraftingManager;
import com.tanderson.player.Player;
import com.tanderson.rds.RDSRandom;

public class GameContext {

    //(Player player, CraftingManager craftingManager, RDSRandom rand, boolean isDevMode)

    private Player player;
    private CraftingManager craftingManager;
    private RDSRandom random;
    private boolean isDevMode;

    public GameContext(Player player, CraftingManager craftingManager, RDSRandom random, boolean isDevMode) {
        this.player = player;
        this.craftingManager = craftingManager;
        this.random = random;
        this.isDevMode = isDevMode;
    }

    public Player getPlayer() {
        return player;
    }

    public CraftingManager getCraftingManager() {
        return craftingManager;
    }

    public RDSRandom getRandom() {
        return random;
    }

    public boolean isDevMode() {
        return isDevMode;
    }

    public void setDevMode(boolean devMode) {
        isDevMode = devMode;
    }
}
