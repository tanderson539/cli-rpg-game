package com.tanderson.systems.map;

import com.tanderson.systems.rds.tables.map.MapTileItemTable;

import java.text.DecimalFormat;
import java.util.Random;

public class Map {

    private MapTile[][] tileMap;
    private int[][] elevationMap;

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
        this.elevationMap = new int[height][width];
        this.generateElevationMap();
        this.tileMap = this.generateRandomMap(width, height);
    }

    public MapTile[][] generateRandomMap(int width, int height) {
        MapTile[][] map = new MapTile[height][width];

        MapTileItemTable tileTable = new MapTileItemTable();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[j][i] = tileTable.runTable();
            }
        }

        return map;
    }

    private void generateElevationMap() {
        Random rand = new Random();
        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {
                elevationMap[x][y] = rand.nextInt(100);
            }
        }
    }

    public String printMap() {
        StringBuilder output = new StringBuilder();

        output.append("+").append("-".repeat(Math.max(0, this.width))).append("+\n|");

        for (int i = 0; i < this.tileMap.length; i++) {
            for (int j = 0; j < this.tileMap[i].length; j++) {
                output.append(this.tileMap[i][j].getSymbol());
            }
            output.append("|\n");
            if(i < this.tileMap.length - 1) {
                output.append("|");
            }
        }

        output.append("+").append("-".repeat(Math.max(0, this.width))).append("+\n");

        return output.toString();
    }

    public String printElevationMap() {
        StringBuilder output = new StringBuilder();
        DecimalFormat decFormat = new DecimalFormat("00");

        for (int i = 0; i < elevationMap.length; i++) {
            for (int j = 0; j < elevationMap[i].length; j++) {
                output.append("[").append(decFormat.format(elevationMap[i][j])).append("]");
            }

            output.append("\n");
        }

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
