package _tests_;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import player.Player;

public class PlayerTests {

    public final Player p = new Player("Test Player");

    @Test
    void testPlayerSkillsInitialization(){
        assertEquals("Test Player", this.p.getPlayerName());
        assertEquals(1, this.p.getPlayerSkills().getMiningSkill().getCurrentLevel());
        assertEquals(0, this.p.getPlayerSkills().getMiningSkill().getTotalXp());
        assertEquals(1, this.p.getPlayerSkills().getWoodcuttingSkill().getCurrentLevel());
        assertEquals(0, this.p.getPlayerSkills().getWoodcuttingSkill().getTotalXp());
        assertEquals(1, this.p.getPlayerSkills().getForgingSkill().getCurrentLevel());
        assertEquals(0, this.p.getPlayerSkills().getForgingSkill().getTotalXp());
    }

    @Test
    void testPlayerNameChange(){
        this.p.setPlayerName("New Player Name");
        assertEquals("New Player Name", this.p.getPlayerName());
    }

    @Test
    void testPlayerMining(){
        this.p.mine();
        assertNotEquals(0, this.p.getInventory().getInventory().size());
        assertEquals(5, this.p.getPlayerSkills().getMiningSkill().getTotalXp());
    }
}
