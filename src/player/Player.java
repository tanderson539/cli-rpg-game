package player;

import items.DroppableItem;
import items.Item;
import rds.tables.OreTable;

public class Player {
    private String playerName;
    private final PlayerSkills playerSkills;

    private final Inventory inventory;

    public Player(String playerName){
        this.playerName = playerName;
        this.playerSkills = new PlayerSkills();
        this.inventory = new Inventory(20);
    }

    public void mine() {
        OreTable table = new OreTable();

        DroppableItem[] oreDropped = table.runTable();

        for(int i = 0; i < oreDropped.length; i++) {
            playerSkills.miningSkill.grantXp(5);
            inventory.addItem((Item) oreDropped[i]);
        }

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
