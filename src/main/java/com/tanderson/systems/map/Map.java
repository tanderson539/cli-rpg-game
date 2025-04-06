package com.tanderson.systems.map;

import com.tanderson.systems.map.tiles.GrassTile;
import com.tanderson.systems.map.tiles.MountainTile;
import com.tanderson.systems.map.tiles.WaterTile;
import com.tanderson.util.FastNoiseLite;

import java.text.DecimalFormat;
import java.util.Random;

public class Map {

    private MapTile[][] tileMap;
    private float[][] noiseMap;

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
        this.noiseMap = new float[height][width];
        this.tileMap = this.generateRandomMap(width, height);
    }

    public MapTile[][] generateRandomMap(int width, int height) {
        MapTile[][] map = new MapTile[height][width];

        this.noiseMap = this.generateNoiseMap();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[j][i] = this.setTileOnNoise(noiseMap[j][i]);
            }
        }

        return map;
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

    public float[][] generateNoiseMap() {
        FastNoiseLite noise = new FastNoiseLite();

        float[][] out = new float[height][width];

        Random rand = new Random();
        int seed = rand.nextInt();

        noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);
        noise.SetSeed(seed);

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                out[y][x] = this.sumOctave(noise, 16, x, y, 1f, 0.07f, -1, 1);
            }
        }

        return out;
    }

    public String printNoiseMap() {
        StringBuilder output = new StringBuilder();
        DecimalFormat decFormat = new DecimalFormat("0.00");

        for (float[] floats : this.noiseMap) {
            for (float aFloat : floats) {
                output.append("[").append(decFormat.format(aFloat)).append("]");
            }

            output.append("\n");
        }

        return output.toString();
    }

    public MapTile setTileOnNoise(float noise) {
        if (noise < 0) {
            return new WaterTile();
        } else if (noise >= 0 && noise < 0.5) {
            return new GrassTile();
        } else {
            return new MountainTile();
        }
    }

    private float sumOctave(FastNoiseLite noiseGen, int num_iterations, int x, int y, float persistence, float scale, float low, float high){
        float maxAmp = 0;
        float amp = 1;
        float freq = scale;
        float noise = 0;

        for(int i = 0; i < num_iterations; i++){
            noise += noiseGen.GetNoise(x * freq, y * freq) * amp;
            maxAmp += amp;
            amp *= persistence;
            freq *= 2;
        }

        noise /= maxAmp;

        noise = noise * (high - low) / 2 + (high + low) / 2;

        return noise;
    }

    public MapTile getTile(int x, int y) {
        return this.tileMap[y][x];
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
