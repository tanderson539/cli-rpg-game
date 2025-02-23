package player;

import skills.MiningSkill;
import skills.WoodcuttingSkill;

public class PlayerSkills {
    MiningSkill miningSkill;
    WoodcuttingSkill woodcuttingSkill;

    public PlayerSkills() {
        this.miningSkill = new MiningSkill();
        this.woodcuttingSkill = new WoodcuttingSkill();
    }

    public MiningSkill getMiningSkill() {
        return miningSkill;
    }

    public WoodcuttingSkill getWoodcuttingSkill() {
        return woodcuttingSkill;
    }

}
