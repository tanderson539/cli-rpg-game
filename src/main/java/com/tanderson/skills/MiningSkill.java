package com.tanderson.skills;

public class MiningSkill extends Skill{

    public MiningSkill() {
        super("Mining");
    }

    public MiningSkill(int totalXp) {
        super("Mining", 99, totalXp);
    }

    public void printMiningLevel(){
        System.out.println("Mining Level: " + this.getCurrentLevel());
        System.out.println("Total XP: " + this.getTotalXp());
        System.out.println("XP to next level: " + this.getXpToNextLevel());
    }
}
