package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.systems.map.Map;

public class RandomMapCommand implements Command {


    @Override
    public String execute(String[] args, GameContext context) {
        int width, height;
        if (args.length == 1) {
            width = 16;
            height = 16;
        } else if (args.length == 2) {
            int x = Integer.parseInt(args[1]);
            width = x;
            height = x;
        } else {
            width = Integer.parseInt(args[1]);
            height = Integer.parseInt(args[2]);
        }


        String out;

        Map map = new Map(width, height);

        out = map.printMap() + "\n\n";

        out += map.printNoiseMap() + "\n";

        return out;
    }
}
