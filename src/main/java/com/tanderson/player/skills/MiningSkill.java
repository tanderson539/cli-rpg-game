package com.tanderson.player.skills;

public class MiningSkill extends Skill{

    public MiningSkill() {
        super("Mining");
    }

    public MiningSkill(int totalXp) {
        super("Mining", 99, totalXp);
    }
}
