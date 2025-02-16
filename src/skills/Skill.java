package skills;

public class Skill {
    private int maxLevel;
    private int currentLevel;
    private int xpToNextLevel;
    private int totalXp;

    private final int BASE_LEVEL_XP = 10;

    private String skillName;

    public Skill(String skillName ) {
        this.skillName = skillName;
        this.maxLevel = 99;
        this.totalXp = 0;
        this.currentLevel = 1;
        this.xpToNextLevel = BASE_LEVEL_XP;
    }

    public Skill(String skillName, int maxLevel ) {
        this.skillName = skillName;
        this.maxLevel = maxLevel;
        this.totalXp = 0;
        this.currentLevel = 1;
        this.xpToNextLevel = BASE_LEVEL_XP;
    }

    public Skill(String skillName, int maxLevel, int totalXp ) {
        this.skillName = skillName;
        this.maxLevel = maxLevel;
        this.totalXp = totalXp;
        this.currentLevel = this.getLevelFromXP(totalXp);
        this.xpToNextLevel = BASE_LEVEL_XP;
    }

    public void grantXp (int xpToGrant){
        this.totalXp += xpToGrant;
        this.xpToNextLevel -= xpToGrant;

        if(this.xpToNextLevel <= 0){
            levelUp();
        }
    }

    public void levelUp(){
        while(totalXp > this.getXpToAnyLevel(this.currentLevel + 1)){
            this.currentLevel++;
            this.xpToNextLevel = (BASE_LEVEL_XP * this.currentLevel) - (totalXp - this.getXpToCurrentLevel());
        }

        System.out.println("You've leveled up " + this.skillName + ". You are now level " + this.currentLevel);
    }

    //returns an int of the amount of XP requird to reach the level just before their current.
    public int getXpToCurrentLevel(){
        int out = 0;
        for(int i = 1; i < this.currentLevel; i++){
            out += BASE_LEVEL_XP * i;
        }

        return out;
    }

    public int getXpToAnyLevel(int lvl){
        int out = 0;
        for(int i = 1; i < lvl; i++){
            out += BASE_LEVEL_XP * i;
        }

        return out;
    }

    public int getLevelFromXP(int xp){
        int lvl = 1;

        while(xp > this.getXpToAnyLevel(lvl + 1)){
            lvl++;
        }

        return lvl;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
    public int getTotalXp() {
        return totalXp;
    }
    public String getSkillName() {return skillName; }
    public int getCurrentLevel() {
        return currentLevel;
    }
    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
    public void setTotalXp(int totalXp) {
        this.totalXp = totalXp;
    }
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    public void setXpToNextLevel(int xpToNextLevel) {
        this.xpToNextLevel = xpToNextLevel;
    }
}
