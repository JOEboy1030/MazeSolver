package com.yo1000.toybox.maze;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MazeSolver {

    private final Element[][] maze;
    private final int height;
    private final int width;

    // 探索するマスと、スタートからの距離
    private static class Node {

        int row;
        int col;
        int distance;

        Node(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    public MazeSolver(Element[][] maze) {
        this.maze = maze;
        this.height = maze.length;
        this.width = maze[0].length;
    }

    public Element[][] solve() {
        int startRow = 0;
        int startCol = 1;
        int goalRow = height - 2;
        int goalCol = width - 1;

        // 各マスまでの最短距離
        int[][] dist = new int[height][width];

        // 移動した際の直前にいたマス
        int[][] prevRow = new int[height][width];
        int[][] prevCol = new int[height][width];

        for (int r = 0; r < height; r++) {
            Arrays.fill(dist[r], Integer.MAX_VALUE); // [自分用].fillはint[]のすべての配列にintを代入する
            Arrays.fill(prevRow[r], -1);
            Arrays.fill(prevCol[r], -1);
        }

        // 距離が小さいマスを優先する
        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(node -> node.distance));

        dist[startRow][startCol] = 0;
        queue.offer(new Node(startRow, startCol, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            int r = current.row;
            int c = current.col;

            // 古い距離情報は無視
            if (current.distance != dist[r][c]) {
                continue;
            }

            if (r == goalRow && c == goalCol) {
                break;
            }

            // 隣接する4方向を調べる
            for (Directions direction : Directions.values()) {
                int nr = r + direction.getDx();
                int nc = c + direction.getDy();

                if (nr < 0 || nr >= height
                        || nc < 0 || nc >= width) {
                    continue;
                }

                if (!maze[nr][nc].isPassable()) {
                    continue;
                }

                // 移動コストを設定（1）
                int newDist = dist[r][c] + 1;

                // より短い経路なら更新
                if (newDist < dist[nr][nc]) {
                    dist[nr][nc] = newDist;
                    prevRow[nr][nc] = r;
                    prevCol[nr][nc] = c;

                    queue.offer(new Node(nr, nc, newDist));
                }
            }
        }

        if (dist[goalRow][goalCol] == Integer.MAX_VALUE) {
            System.out.println("経路が見つかりません。");
            return maze;
        }

        // ゴールからスタートまで戻り、経路を描画用に記録
        int currentRow = goalRow;
        int currentCol = goalCol;

        while (currentRow != startRow || currentCol != startCol) {
            maze[currentRow][currentCol] = Element.ROUTE;

            //直接代入するとcolが別の位置を参照してしまうため一時変数を使用中
            int previousRow = prevRow[currentRow][currentCol];
            int previousCol = prevCol[currentRow][currentCol];

            currentRow = previousRow;
            currentCol = previousCol;
        }
        
        maze[startCol][startRow] = Element.ROUTE;
        System.out.println("最短距離: " + dist[goalRow][goalCol]);
        return maze;
    }
}
