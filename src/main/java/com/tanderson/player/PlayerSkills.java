package com.tanderson.player;

import com.tanderson.skills.ForgingSkill;
import com.tanderson.skills.MiningSkill;
import com.tanderson.skills.WoodcuttingSkill;

/**
 * Catch-all class for handling every skill a player will have access to.
 */
public class PlayerSkills {
    private final MiningSkill miningSkill;
    private final WoodcuttingSkill woodcuttingSkill;
    private final ForgingSkill forgingSkill;

    /**
     * Default constructor that creates all skills with 0 total XP and a current level of 1.
     */
    public PlayerSkills() {
        this.miningSkill = new MiningSkill();
        this.woodcuttingSkill = new WoodcuttingSkill();
        this.forgingSkill = new ForgingSkill();
    }

    /**
     * Creates all skills with a given totalXP for each skill.
     * Is there a better way to do this?
     * What if I add 20+ more skills?
     * Great question, me. That is a bridge I will cross when I come to it.
     * @param miningXp Integer totalXP value to determine level and more.
     * @param woodcuttingXp Integer totalXP value to determine level and more.
     * @param forgingXp Integer totalXP value to determine level and more.
     */
    public PlayerSkills(int miningXp, int woodcuttingXp, int forgingXp) {
        this.miningSkill = new MiningSkill(miningXp);
        this.woodcuttingSkill = new WoodcuttingSkill(woodcuttingXp);
        this.forgingSkill = new ForgingSkill(forgingXp);
    }

    /**
     * Returns the miningSkill object.
     * @return The miningSkill object.
     */
    public MiningSkill getMiningSkill() {
        return this.miningSkill;
    }

    /**
     * Returns the woodcuttingSkill object.
     * @return The woodcuttingSkill object.
     */
    public WoodcuttingSkill getWoodcuttingSkill() {
        return this.woodcuttingSkill;
    }

    /**
     * Returns the forgingSkill object.
     * @return The forgingSkill object.
     */
    public ForgingSkill getForgingSkill() { return this.forgingSkill; }

}
