package com.yo1000.toybox.maze;

public record MazeSize(int width, int height) {

    public MazeSize {
        if (width < 5 || height < 5) {
            throw new IllegalArgumentException(
                    "WidthとHeightは5以上である必要があります。");
        }
        if (width % 2 == 0 || height % 2 == 0) {
            throw new IllegalArgumentException(
                    "WidthとHeightは奇数である必要があります。");
        }
    }
}
