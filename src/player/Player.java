package player;

import items.ItemRecord;
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

        ItemRecord[] ores = table.runTable();

        for (ItemRecord ore : ores) {
            if (ore != null) {
                playerSkills.miningSkill.grantXp(5);
                inventory.addItem(ore.getItem(), ore.getAmount());
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
