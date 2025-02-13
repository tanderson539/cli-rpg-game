import skills.*;

public class Player {
    private String playerName;
    private MiningSkill miningLevel;

    public Player(String playerName){
        this.playerName = playerName;
        this.miningLevel = new MiningSkill();
    }

    public void mine() {
        miningLevel.grantXp(5);
        System.out.println("You mined some ore!");
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
}
