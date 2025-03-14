package com.tanderson.command.commands;

import com.tanderson.GameContext;

public class ExitCmd implements Command {

    @Override
    public String execute(String[] args, GameContext context) {
        System.exit(0);
        return "exiting...";
    }
}
