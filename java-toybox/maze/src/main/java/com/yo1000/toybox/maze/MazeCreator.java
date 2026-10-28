package com.yo1000.toybox.maze;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class MazeCreator {

    private final Element[][] maze;
    private final int height;
    private final int width;
    private final RandomGenerator random = new SecureRandom();

    public MazeCreator(Element[][] maze, int height, int width) {
        this.maze = maze;
        this.height = height;
        this.width = width;
    }

    public MazeCreator(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new Element[height][width];
    }

    public MazeCreator(MazeSize mazeSize) {
        this.height = mazeSize.height();
        this.width = mazeSize.width();
        this.maze = new Element[height][width];
    }

    public Element[][] create() {
        createOuterWall();
        createPillar();
        extendWalls();
        return maze;
    }

    private void createOuterWall() {
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (y == 0 || y == width - 1 || x == 0 || x == height - 1) {
                    maze[x][y] = Element.WALL;
                } else {
                    maze[x][y] = Element.PASSAGE;
                }
            }
        }
    }

    private void createPillar() {
        for (int x = 2; x < height - 1; x += 2) {
            for (int y = 2; y < width - 1; y += 2) {
                maze[x][y] = Element.WALL;
            }
        }
    }

    private void extendWalls() {
        for (int x = 2; x < height - 1; x += 2) {
            for (int y = 2; y < width - 1; y += 2) {
                List<Directions> directions = new ArrayList<>();
                if (x == 2) {
                    directions.add(Directions.TOP);// 一番上のみ上方向に追加可能
                }
                directions.add(Directions.BOTTOM);
                directions.add(Directions.LEFT);
                directions.add(Directions.RIGHT);

                List<Directions> walls = new ArrayList<>();
                for (Directions d : directions) {
                    int nx = x + d.getDx();
                    int ny = y + d.getDy();
                    if (maze[nx][ny] != Element.WALL) {
                        walls.add(d);
                    }
                }

                if (!walls.isEmpty()) {
                    Directions direction = walls.get(random.nextInt(walls.size()));

                    int nx = x + direction.getDx();
                    int ny = y + direction.getDy();

                    maze[nx][ny] = Element.WALL;
                }
            }
        }
        // スタート、ゴールの壁を開通
        maze[1][0] = Element.PASSAGE;
        maze[height - 2][width - 1] = Element.PASSAGE;
    }
}
