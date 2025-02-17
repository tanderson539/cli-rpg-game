package rds;

import java.util.Random;

public class RDSRandom {
    private Random rand;
    private int seed;

    public RDSRandom() {
        rand = new Random();
    }

    public RDSRandom(int seed) {
        rand = new Random(seed);
    }

    public int genInt(int max){
        return rand.nextInt(max);
    }

    public int genInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public double genDouble() {
        return rand.nextDouble();
    }

    public double genDouble(double max) {
        return rand.nextDouble(max);
    }

    public double genDouble(double min, double max) {
        return rand.nextDouble((max - min) + 1) + min;
    }

}
