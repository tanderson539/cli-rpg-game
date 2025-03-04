package com.tanderson.player;

import com.tanderson.skills.ForgingSkill;
import com.tanderson.skills.MiningSkill;
import com.tanderson.skills.WoodcuttingSkill;

public class PlayerSkills {
    MiningSkill miningSkill;
    WoodcuttingSkill woodcuttingSkill;
    ForgingSkill forgingSkill;

    public PlayerSkills() {
        this.miningSkill = new MiningSkill();
        this.woodcuttingSkill = new WoodcuttingSkill();
        this.forgingSkill = new ForgingSkill();
    }

    public MiningSkill getMiningSkill() {
        return this.miningSkill;
    }

    public WoodcuttingSkill getWoodcuttingSkill() {
        return this.woodcuttingSkill;
    }

    public ForgingSkill getForgingSkill() { return this.forgingSkill; }

}
