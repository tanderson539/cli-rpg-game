package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.systems.map.MapTile;
import com.tanderson.systems.rds.tables.map.MapTileItemTable;

public class MapTileCommand implements Command {

    @Override
    public String execute(String[] args, GameContext context) {
        MapTileItemTable mapTileItemTable = new MapTileItemTable();

        MapTile result = mapTileItemTable.runTable();

        return "Result: " + result.getSymbol();
    }
}
