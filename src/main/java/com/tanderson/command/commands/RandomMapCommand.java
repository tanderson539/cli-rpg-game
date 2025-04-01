package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.systems.map.Map;

public class RandomMapCommand implements Command {


    @Override
    public String execute(String[] args, GameContext context) {
        int mapSize = 16;

        if(args.length >= 2) mapSize = Integer.parseInt(args[1]);

        Map map = new Map(mapSize, mapSize);

        return map.printMap();
    }
}
