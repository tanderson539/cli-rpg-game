package com.tanderson.command.commands;

import com.tanderson.GameContext;

public class HelloCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        return "Hi!";
    }
}
