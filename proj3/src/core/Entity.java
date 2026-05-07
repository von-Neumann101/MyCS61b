package core;

import tileengine.Tileset;

import java.util.LinkedList;
import java.util.Queue;

import static core.Main.gameOver;
import static core.World.HEIGHT;
import static core.World.WIDTH;

public class Entity {
    Point position;

    Entity(Point position) {
        this.position = position;
    }

    /**
     * 每个格点到玩家最近距离
     * @param player 玩家位置
     * @return 一个包含每个格点到玩家位置的距离的二维数组
     */
    private int[][] bfsDistanceFromUser(Point player, World world) {
        int[][] dist = new int[WIDTH][HEIGHT]; // 到玩家的最短距离

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                dist[i][j] = -1; //-1为目前不可及
            }
        }

        Queue<Point> queue = new LinkedList<>();

        dist[player.x][player.y] = 0;
        queue.add(player);//从玩家处开始扩散

        int[][] direction = {
                {1, 0},//→
                {-1, 0},//←
                {0, 1},//↑
                {0, -1}//↓
        };

        while (!queue.isEmpty()) {
            Point cur = queue.remove();

            for (int [] dir : direction) { // 遍历各个方向
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];

                if (world.isWalkable(new Point(nx, ny)) && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[cur.x][cur.y] + 1;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        return dist;
    }

    private Point entityNextPosition(Point entity, int[][] dist) {
        int[][] direction = {
                {1, 0},//→
                {-1, 0},//←
                {0, 1},//↑
                {0, -1}//↓
        };

        Point best = entity;
        int best_dist = dist[entity.x][entity.y];

        if (best_dist == -1) {
            return entity; //-1位置到不了玩家，所以直接返回原位置（停止）
        }

        for (int[] dir : direction) {
            int nx = entity.x + dir[0];
            int ny = entity.y + dir[1];

            if (nx < 0 || nx >= WIDTH || ny < 0 || ny >= HEIGHT) {
                continue;
            }

            if (dist[nx][ny] != -1 && dist[nx][ny] < best_dist) {
                best_dist = dist[nx][ny];
                best = new Point(nx, ny);
            }
        }
        return best;
    }

    /**
     * entity的移动逻辑
     * @param player 玩家位置
     * @param world 世界
     */
    public void moveEntity(Point player, World world) {
        int[][] dist = bfsDistanceFromUser(player, world);

        Point next = entityNextPosition(position, dist);

        if (next.x == player.x && next.y == player.y) {
            gameOver();
            return;
        }

        world.world[position.x][position.y] = Tileset.FLOOR;
        position = next;
        world.world[position.x][position.y] = Tileset.ENTITY;
    }
}
