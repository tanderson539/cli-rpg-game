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
        //TODO: When functionality to item amounts are added to RDSTables, refactor to include item amounts
        OreTable table = new OreTable();

        DroppableItem[] oreDropped = table.runTable();

        for (DroppableItem droppableItem : oreDropped) {
            if (droppableItem != null) {
                playerSkills.miningSkill.grantXp(5);
                inventory.addItem((Item) droppableItem);
                System.out.println("You mined some " + droppableItem.getName());
            }
        }
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
