package com.yo1000.toybox.maze;

// 迷路・ルート描画に使用する文字を定義
public enum Element {
    WALL("██", false),
    PASSAGE("  ", true),
    ROUTE("\u001B[44m  \u001B[0m", true);

    private final String displayText;
    private final boolean passable;

    Element(String displayText, boolean passable) {
        this.displayText = displayText;
        this.passable = passable;
    }

    public String getDisplayText() {
        return displayText;
    }

    public boolean isPassable() {
        return passable;
    }

}
