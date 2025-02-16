package player;

import skills.MiningSkill;

public class PlayerSkills {
    MiningSkill miningSkill;

    public PlayerSkills() {
        this.miningSkill = new MiningSkill();
    }

    public MiningSkill getMiningSkill() {
        return miningSkill;
    }

}
