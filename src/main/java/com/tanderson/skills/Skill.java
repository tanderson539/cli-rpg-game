package com.tanderson.skills;

/**
 * Base class to build any and all skills the game will use.
 * TODO: Make the maxLevel matter somehow?
 */
public class Skill {
    private int maxLevel;
    private int currentLevel;
    private int xpToNextLevel;
    private int totalXp;

    private final int BASE_LEVEL_XP = 10;

    private String skillName;

    /**
     * Initializes a skill with a name, defaults maxLevel to 99, totalXp to 0, currentLevel to 1, and xpToNextLevel
     * to whatever the base XP is.
     * @param skillName A string name of the skill being created.
     */
    public Skill(String skillName ) {
        this.skillName = skillName;
        this.maxLevel = 99;
        this.totalXp = 0;
        this.currentLevel = 1;
        this.xpToNextLevel = BASE_LEVEL_XP;
    }

    /**
     * Initializes a skill with a name and maximum level. Defaults totalXp to 0, currentLevel to 1, and xpToNextLevel
     * to whatever the base XP is.
     * @param skillName A string name of the skill being created.
     * @param maxLevel An integer maximum possible level to reach as a player.
     */
    public Skill(String skillName, int maxLevel ) {
        this.skillName = skillName;
        this.maxLevel = maxLevel;
        this.totalXp = 0;
        this.currentLevel = 1;
        this.xpToNextLevel = BASE_LEVEL_XP;
    }

    /**
     * Initializes a skill with a name and maximum level, and total XP. Uses totalXP to determine what level the player
     * is at and sets currentLevel to that value
     * @param skillName A string name of the skill being created.
     * @param maxLevel An integer maximum possible level to reach as a player.
     * @param totalXp An integer representing the total xp the player has in this skill.
     */
    public Skill(String skillName, int maxLevel, int totalXp ) {
        this.skillName = skillName;
        this.maxLevel = maxLevel;
        this.totalXp = totalXp;
        this.currentLevel = this.getLevelFromXP(totalXp);
        this.xpToNextLevel = (BASE_LEVEL_XP * this.currentLevel) - (this.totalXp - this.getXpToCurrentLevel());;
    }

    /**
     * Adds a specified amount to the skill's totalXP, reduces xpToNextLevel by the same,
     * and determines if a level up is granted.
     * @param xpToGrant Integer XP amount to add to totalXP.
     */
    public void grantXp (int xpToGrant){
        this.totalXp += xpToGrant;
        this.xpToNextLevel -= xpToGrant;

        if(this.xpToNextLevel <= 0){
            levelUp();
        }
    }

    /**
     * Uses a while loop to determine if multiple level-ups are granted, then sets currentLevel, xpToNextLevel,
     * and then prints out that a level up occurred and the level reached.
     */
    public void levelUp(){
        while(totalXp >= this.getXpToAnyLevel(this.currentLevel + 1)){
            this.currentLevel++;
            this.xpToNextLevel = (this.BASE_LEVEL_XP * this.currentLevel) - (this.totalXp - this.getXpToCurrentLevel());
        }

        System.out.println("You've leveled up " + this.skillName + ". You are now level " + this.currentLevel);
    }

    /**
     * returns an Integer amount of XP required to reach the current level.
     * @return An Integer amount of XP required to reach the current level.
     */
    public int getXpToCurrentLevel(){
        int out = 0;
        for(int i = 1; i < this.currentLevel; i++){
            out += this.BASE_LEVEL_XP * i;
        }

        return out;
    }

    /**
     * Takes a given Integer lvl and calculates how much xp it would take to exactly reach that level.
     * @param lvl An Integer level to calculate Xp to reach.
     * @return An integer total xp amount reach the given level.
     */
    public int getXpToAnyLevel(int lvl){
        int out = 0;
        for(int i = 1; i < lvl; i++){
            out += this.BASE_LEVEL_XP * i;
        }

        return out;
    }

    /**
     * Takes a given Integer total xp amount and calculates what level that total xp grants a player.
     * @param xp Integer amount of total xp.
     * @return Returns the level granted by the total xp given.
     */
    public int getLevelFromXP(int xp){
        int lvl = 1;

        while(xp > this.getXpToAnyLevel(lvl + 1)){
            lvl++;
        }

        return lvl;
    }

    /**
     * Prints info about this specific skill to the console.
     * Checks if the currentLevel is below the max level, and if it is, also prints xp to next level.
     */
    public void printSkillInfo(){
        System.out.println(this.skillName + " Level: " + this.getCurrentLevel());
        System.out.println("Total XP: " + this.getTotalXp());

        if(this.getCurrentLevel() < this.getMaxLevel()){
            System.out.println("XP to next level: " + this.getXpToNextLevel());
        }
    }

    /**
     * Returns the configured Integer max level.
     * @return The configured Integer max level.
     */
    public int getMaxLevel() {
        return this.maxLevel;
    }

    /**
     * Returns the current Integer totalXp.
     * @return The current Integer totalXp.
     */
    public int getTotalXp() {
        return this.totalXp;
    }

    /**
     * Returns the String skill name.
     * @return The String skill name.
     */
    public String getSkillName() {return this.skillName; }

    /**
     * Returns the Integer current level.
     * @return The Integer current level
     */
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    /**
     * Returns the Integer amount of xp to the next level.
     * @return The Integer amount of xp to the next level.
     */
    public int getXpToNextLevel() {
        return this.xpToNextLevel;
    }

    /**
     * Sets the Integer max level of this specific skill.
     * @param maxLevel the Integer max level of this specific skill.
     */
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    /**
     * Sets the Integer total amount of xp for this specific skill.
     * @param totalXp The Integer total amount of xp for this specific skill.
     */
    //TODO: ensure setTotalXP also sets the currentLevel and xpToNextLevel
    public void setTotalXp(int totalXp) {
        this.totalXp = totalXp;
        this.setCurrentLevel();
        this.setXpToNextLevel();
    }

    /**
     * Sets the String name for this specific skill.
     * @param skillName The String name for this specific skill.
     */
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    /**
     * Sets xpToNextLevel based on the current level and totalXp.
     */
    private void setXpToNextLevel() {
        int xpNeededForNextLevel = this.getXpToAnyLevel(this.currentLevel + 1);

        this.xpToNextLevel = xpNeededForNextLevel - totalXp;
    }

    /**
     * Sets the current level based on totalXp.
     */
    private void setCurrentLevel(){
        this.currentLevel = this.getLevelFromXP(totalXp);
    }
}
