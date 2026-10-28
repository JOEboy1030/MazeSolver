package com.yo1000.toybox.maze;

public enum Directions {
    TOP(-1, 0),
    BOTTOM(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int dx;
    private final int dy;

    Directions(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
