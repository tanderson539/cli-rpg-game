package com.tanderson.command.commands;

import com.tanderson.GameContext;
import com.tanderson.command.RegisteredSlashCommand;
import com.tanderson.systems.map.MapTile;
import com.tanderson.systems.rds.tables.map.MapTileItemTable;

@RegisteredSlashCommand(aliases = {"tile"})
public class MapTileCmd implements Command {

    @Override
    public String execute(String[] args, GameContext context) {
        MapTileItemTable mapTileItemTable = new MapTileItemTable();

        MapTile result = mapTileItemTable.runTable();

        return "Result: " + result.getSymbol();
    }
}
