package com.tanderson.command.commands;

import com.tanderson.GameContext;

public interface Command {
    String execute(String[] args, GameContext context);
}
