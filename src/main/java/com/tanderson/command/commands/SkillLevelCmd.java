package com.tanderson.command.commands;

import com.tanderson.GameContext;

public class SkillLevelCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        return context.getPlayer().getPlayerSkills().getMiningSkill().printSkillInfo();
    }
}
