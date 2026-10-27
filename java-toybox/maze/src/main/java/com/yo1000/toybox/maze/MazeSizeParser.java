package com.yo1000.toybox.maze;

import java.util.Optional;

public class MazeSizeParser {

    public static final int DEFAULT_WIDTH = 20;
    public static final int DEFAULT_HEIGHT = 10;

    public MazeSizeParser() {
    }

    public static Optional<MazeSize> parseMazeSize(String[] args) {
        int width = DEFAULT_WIDTH;
        int height = DEFAULT_HEIGHT;

        if (args.length >= 1) {
            try {
                width = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println(
                        "数値ではない値を受け取ったので、Width20で実装します。");
            }
        }

        if (args.length >= 2) {
            try {
                height = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println(
                        "数値ではない値を受け取ったので、Height10で実装します。");
            }
        }

        if (width % 2 == 0) {
            System.out.println(
                    "Widthを奇数に変換するため、＋１します。");
            width++;
        }

        if (height % 2 == 0) {
            System.out.println(
                    "Heightを奇数に変換するため、＋１します。");
            height++;
        }

        MazeSize mazeSize = new MazeSize(width, height);
        System.out.println("Width : " + mazeSize.width());
        System.out.println("Height: " + mazeSize.height() + "\n");
        return Optional.of(mazeSize);
    }
}
