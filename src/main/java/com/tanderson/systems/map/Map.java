package com.tanderson.systems.map;

import com.tanderson.systems.rds.tables.map.MapTileItemTable;

public class Map {

    private MapTile[][] tileMap;

    private int width;
    private int height;

    public Map(int width, int height, MapTile[][] tileMap) {
        this.tileMap = tileMap;
        this.width = width;
        this.height = height;
    }

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.tileMap = this.generateRandomMap(width, height);
    }

    public MapTile[][] generateRandomMap(int width, int height) {
        MapTile[][] map = new MapTile[width][height];

        MapTileItemTable tileTable = new MapTileItemTable();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = tileTable.runTable();
            }
        }

        return map;
    }

    public String printMap() {
        StringBuilder output = new StringBuilder();

        output.append("+").append("-".repeat(Math.max(0, width))).append("+\n|");

        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                output.append(tileMap[i][j].getSymbol());
            }
            output.append("|\n");
            if(i < tileMap.length - 1) {
                output.append("|");
            }
        }

        output.append("+").append("-".repeat(Math.max(0, width))).append("+\n");

        return output.toString();
    }

    public MapTile[][] getTileMap() {
        return tileMap;
    }

    public void setTileMap(MapTile[][] tileMap) {
        this.tileMap = tileMap;
        this.width = this.tileMap.length;
        this.height = this.tileMap[0].length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
