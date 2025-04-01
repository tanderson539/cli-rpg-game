package com.tanderson.systems.map;

public class MapTile {

    private Point point;
    private char symbol;
    private boolean traverse;

    public MapTile(Point point, char symbol, boolean traverse) {
        this.point = point;
        this.symbol = symbol;
    }

    public MapTile(int x, int y, char symbol, boolean traverse) {
        this.point = new Point(x, y);
        this.symbol = symbol;
    }

    public MapTile(char symbol, boolean traverse) {
        this.symbol = symbol;
        this.traverse = traverse;
    }

    public void move(int dx, int dy) {
        this.point.move(dx, dy);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean canTraverse() {
        return traverse;
    }

    public void setTraverse(boolean traverse) {
        this.traverse = traverse;
    }
}
