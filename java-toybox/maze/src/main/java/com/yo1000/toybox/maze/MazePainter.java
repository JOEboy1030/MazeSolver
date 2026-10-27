package com.yo1000.toybox.maze;

public class MazePainter {
    public static void draw(Element[][] maze) {
        // 位置調整のためスペース挿入
        System.out.print(" ");
        for (int y = 0; y < maze[0].length; y++) {
            System.out.printf("%2d", y % 10);
        }
        System.out.println();
        for (int x = 0; x < maze.length; x++) {
            System.out.printf("%2d", x % 10);
            for (int y = 0; y < maze[0].length; y++) {
                System.out.print(maze[x][y].getDisplayText());
            }
            System.out.println();
        }
        System.out.println();
    }
}
