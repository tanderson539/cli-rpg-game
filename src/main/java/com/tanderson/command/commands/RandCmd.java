package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.log.LogLevel;
import org.apache.commons.lang3.StringUtils;

public class RandCmd implements Command {
    @Override
    public String execute(String[] args, GameContext context) {
        double max = 20.0;
        String out = "";

        if(context.isDevMode()) {
            if(args.length > 1){
                if(StringUtils.isNumeric(args[1])){
                    max = Double.parseDouble(args[1]);
                }
            }
            context.getLogger().log("Player ran rand command with max of: " + max, LogLevel.INFO);
            out += ("Rand (max possible: " + max + "):\n" + context.getRandom().genDouble(max));
        } else {
            context.getLogger().log("Player attempted to run rand while not in Dev Mode.", LogLevel.INFO);
            out += "Invalid command";
        }
        return out;
    }
}
