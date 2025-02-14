package player;

import items.ores.Ore_Copper;
import skills.*;

public class Player {
    private String playerName;
    private MiningSkill miningLevel;

    private Inventory inventory;

    public Player(String playerName){
        this.playerName = playerName;
        this.miningLevel = new MiningSkill();
        this.inventory = new Inventory(20);
    }

    public void mine() {
        miningLevel.grantXp(5);
        inventory.addItem(new Ore_Copper());
        System.out.println("You mined some ore!");
    }

    public void printMiningLevel(){
        System.out.println(this.getPlayerName() + "'s Mining Level: " + this.getMiningLevel());
        System.out.println("Total XP: " + this.getMiningXp());
        System.out.println("XP to next level: " + this.getMiningXpToNextLevel());
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMiningLevel() {
        return miningLevel.getCurrentLevel();
    }

    public int getMiningXp(){
        return miningLevel.getTotalXp();
    }

    public int getMiningXpToNextLevel(){
        return miningLevel.getXpToNextLevel();
    }

    public Inventory getInventory(){
        return this.inventory;
    }
}
