package com.tanderson.systems.map;

import com.tanderson.systems.map.tiles.WaterTile;
import com.tanderson.systems.rds.tables.map.MapTileItemTable;

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
        MapTile[][] map = new MapTile[width][height];

        MapTileItemTable tileTable = new MapTileItemTable();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = tileTable.runTable();
            }
        }

        this.generateRiver(width / 2, 0);

        return map;
    }

    private void generateElevationMap() {
        Random rand = new Random();
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                elevationMap[x][y] = rand.nextInt(100);
            }
        }
    }

    private void generateRiver(int x, int y) {
        while (y < this.height - 1) { // Stop at the bottom of the map
            tileMap[x][y] = new WaterTile(new Point(x, y)); // Mark as river

            int lowestX = x;
            int lowestY = y;
            int lowestElevation = elevationMap[x][y];

            // Check all adjacent tiles
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = x + dx;
                    int ny = y + dy;

                    if (nx >= 0 && nx < this.width && ny >= 0 && ny < this.height) {
                        if (elevationMap[nx][ny] < lowestElevation) {
                            lowestX = nx;
                            lowestY = ny;
                            lowestElevation = elevationMap[nx][ny];
                        }
                    }
                }
            }

            // If no lower neighbor is found, stop
            if (lowestX == x && lowestY == y) break;

            x = lowestX;
            y = lowestY;
        }
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
