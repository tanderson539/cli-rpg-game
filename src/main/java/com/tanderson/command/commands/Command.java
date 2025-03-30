package com.tanderson.command.commands;

import com.tanderson.GameContext;

/**
 * Interface for all command-line runnable commands
 */
public interface Command {

    /**
     * Template command to execute a command-line command.
     * @param args A String array of the entire command, args separated by a space.
     * @param context Game context object, allowing access to all objects in the context.
     * @return A String output to be printed to the console.
     */
    String execute(String[] args, GameContext context);
}
