package com.yo1000.toybox.maze;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Optional<MazeSize> size = MazeSizeParser.parseMazeSize(args);
        if (size.isEmpty()) {
            return;
        }
        MazeSize mazeSize = size.get();

        // 迷路作成
        Element[][] maze = new MazeCreator(mazeSize.height(), mazeSize.width()).create();
        MazePainter.draw(maze);

        // 経路探索
        // 経路探索クラスのコンストラクタをここで呼び出しておく
        Element[][] mazeSolve = new MazeSolver(maze).solve();
        MazePainter.draw(mazeSolve);
    }
}
