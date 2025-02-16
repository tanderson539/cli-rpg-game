package player;

import items.ores.Ore_Copper;
import skills.*;

public class Player {
    private String playerName;
    private PlayerSkills playerSkills;

    private Inventory inventory;

    public Player(String playerName){
        this.playerName = playerName;
        this.playerSkills = new PlayerSkills();
        this.inventory = new Inventory(20);
    }

    public void mine() {
        playerSkills.miningSkill.grantXp(31);
        inventory.addItem(new Ore_Copper(1, false, true, false));

        System.out.println("You mined some ore!");
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public PlayerSkills getPlayerSkills(){
        return playerSkills;
    }
}
