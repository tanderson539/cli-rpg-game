package com.tanderson.command.commands;

import com.tanderson.GameContext;

/**
 * Handles logic for performing the 'level' command.
 * Prints out the player's levels for each skill in a human-readable format.
 */
public class SkillLevelCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        String out = "";
        out += context.getPlayer().getPlayerSkills().getMiningSkill().printSkillInfo() + "\n\n";
        out += context.getPlayer().getPlayerSkills().getWoodcuttingSkill().printSkillInfo() + "\n\n";
        out += context.getPlayer().getPlayerSkills().getForgingSkill().printSkillInfo() + "\n";
        return out;
    }
}
