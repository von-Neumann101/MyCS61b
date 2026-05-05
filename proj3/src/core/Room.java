package core;

import tileengine.TETile;

import java.util.Objects;

import static tileengine.Tileset.FLOOR;
import static tileengine.Tileset.WALL;

public class Room {
    TETile[][] world;
    private static int WIDTH;
    private static int HEIGHT;
    int x, y;
    int width, height;

    public Room(TETile[][] world, int x, int y, int w, int h) {
        this.world = world;
        WIDTH = world.length;
        HEIGHT = world[0].length;
        width = w;
        height = h;
        this.x = x;
        this.y = y;
    }

    /**
     * 绘制Room
     */
    void draw() {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return;

        if (x + width > WIDTH) width = WIDTH - x;
        if (y + height > HEIGHT) height = HEIGHT - y;

        if (width < 3 || height < 3) return;


        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if (i == x || i == x + width - 1 || j == y || j == y + height - 1) {
                    world[i][j] = WALL;
                } else {
                    world[i][j] = FLOOR;
                }
            }
        }
    }

    public int centerX() {
        return x + width / 2;
    }

    public int centerY() {
        return y + height / 2;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Room r)) return false;

        return Objects.equals(x, r.x) && Objects.equals(y, r.y) && Objects.equals(width, r.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }
}
