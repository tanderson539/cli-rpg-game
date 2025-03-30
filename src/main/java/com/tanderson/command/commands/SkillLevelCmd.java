package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.log.LogLevel;

/**
 * Handles logic for performing the 'level' command.
 * Prints out the player's levels for each skill in a human-readable format.
 */
public class SkillLevelCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        context.getLogger().log("Player ran level command.", LogLevel.INFO);
        String out = "";
        out += context.getPlayer().getPlayerSkills().getMiningSkill().printSkillInfo() + "\n\n";
        out += context.getPlayer().getPlayerSkills().getWoodcuttingSkill().printSkillInfo() + "\n\n";
        out += context.getPlayer().getPlayerSkills().getForgingSkill().printSkillInfo() + "\n";
        return out;
    }
}
