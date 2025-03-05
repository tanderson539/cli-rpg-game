package com.tanderson;

import com.tanderson.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Player Test")
public class PlayerTests {

    public final Player p = new Player("Test Player");

    @Test
    @DisplayName("is instantiated with the correct properties.")
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
    @DisplayName("is able to change the player's name.")
    void testPlayerNameChange(){
        this.p.setPlayerName("New Player Name");
        assertEquals("New Player Name", this.p.getPlayerName());
    }

    @Test
    @DisplayName("is able to perform a mining action and gain xp.")
    void testPlayerMining(){
        this.p.mine();
        assertNotEquals(0, this.p.getInventory().getInventory().size());
        assertTrue(this.p.getPlayerSkills().getMiningSkill().getTotalXp() > 0);
    }
}
