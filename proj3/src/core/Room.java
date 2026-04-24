package core;

import tileengine.TETile;
import tileengine.Tileset;

import static tileengine.Tileset.FLOOR;
import static tileengine.Tileset.WALL;

public class Room {
    TETile[][] world;
    private final PointSet door;
    private static int WIDTH;
    private static int HEIGHT;
    private static int index = 0;
    int id;
    public Room(TETile[][] world) {
        door = new PointSet();
        this.world = world;
        WIDTH = world.length;
        HEIGHT = world[0].length;
        this.id = index++;
    }

    /**
     * 绘制矩形
     * @param x 矩形左下角横坐标
     * @param y 矩形左下角纵坐标
     * @param width 宽
     * @param height 高
     */
    void drawRectangle(int x, int y, int width, int height) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return;

        if (x + width > WIDTH) width = WIDTH - x;
        if (y + height > HEIGHT) height = HEIGHT - y;

        if (width < 3 || height < 3) return;
        if (!isEmptyArea(x, y, width, height)) return;

        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if (i == x || i == x + width - 1 || j == y || j == y + height - 1) {
                    world[i][j] = WALL;
                } else {
                    world[i][j] = FLOOR;
                }
            }
        }

        int midX = x + width / 2;   // 偶数时偏右
        int midY = y + height / 2;  // 偶数时偏上

        if (midX > 0 && midX < WIDTH - 1 && y > 0 && y < HEIGHT - 1) {
            door.add(midX, y);
        }
        if (midX > 0 && midX < WIDTH - 1 && y + height - 1 > 0 && y + height - 1 < HEIGHT - 1) {
            door.add(midX, y + height - 1);
        }
        if (x > 0 && x < WIDTH - 1 && midY > 0 && midY < HEIGHT - 1) {
            door.add(x, midY);
        }
        if (x + width - 1 > 0 && x + width - 1 < WIDTH - 1 && midY > 0 && midY < HEIGHT - 1) {
            door.add(x + width - 1, midY);
        }
    }

    private boolean isEmptyArea(int x, int y, int width, int height) {
        int W = world.length;
        int H = world[0].length;
        if (x < 0 || y < 0 || x + width > W || y + height > H) {
            return false;
        }
        if (width < 3 || height < 3) {
            return false;
        }
        for (int i = x + 1; i < x + width - 1; i++) {
            for (int j = y + 1; j < y + height - 1; j++) {
                if (world[i][j] != Tileset.NOTHING) {
                    return false;
                }
            }
        }
        return true;
    }
}
