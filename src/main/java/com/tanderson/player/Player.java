package com.tanderson.player;

import com.tanderson.player.skills.managers.MiningManager;

/**
 * Handles all data representing a player.
 */
public class Player {
    private String playerName;
    private final PlayerSkills playerSkills;
    private final MiningManager miningManager;

    private final Inventory inventory;

    /**
     * Configures a Player object with a provided name and default skills.
     * All skills default to Level 1 with 0 XP. Defaults to a completely empty inventory.
     * Configured a MiningManager to handle any mining actions.
     * @param playerName A string name for the player.
     */
    public Player(String playerName){
        this.playerName = playerName;
        this.playerSkills = new PlayerSkills();
        this.inventory = new Inventory(20);
        this.miningManager = new MiningManager(this.playerSkills.getMiningSkill(), this.inventory);
    }

    /**
     * Configures a Player object with a provided name and pre-configured skills.
     * Defaults to a completely empty inventory.
     * Configured a MiningManager to handle any mining actions.
     * @param playerName A string name for the player.
     * @param playerSkills An object holding pre-configured player Skills. Allows for loading skill for testing or from a save.
     */
    public Player(String playerName, PlayerSkills playerSkills){
        this.playerName = playerName;
        this.playerSkills = playerSkills;
        this.inventory = new Inventory(20);
        this.miningManager = new MiningManager(this.playerSkills.getMiningSkill(), this.inventory);
    }

    /**
     * Performs a Mining action and grants XP in the mining skill.
     */
    public void mine() {
        miningManager.mine();
    }

    /**
     * Returns the String name for the player.
     * @return The String name for the player.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the String name for the player.
     * @param playerName The String name for the player.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Returns the Inventory object associated with this Player.
     * @return The Inventory object associated with this Player.
     */
    public Inventory getInventory(){
        return this.inventory;
    }

    /**
     * Returns an object representing all associated player skills and their data.
     * @return an object representing all associated player skills and their data.
     */
    public PlayerSkills getPlayerSkills(){
        return playerSkills;
    }
}
