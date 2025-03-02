package player;

import skills.ForgingSkill;
import skills.MiningSkill;
import skills.WoodcuttingSkill;

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
