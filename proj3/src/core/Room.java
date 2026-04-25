package core;

import tileengine.TETile;

import java.util.Objects;

import static tileengine.Tileset.FLOOR;
import static tileengine.Tileset.WALL;

public class Room {
    TETile[][] world;
    private final PointSet door;
    private static int WIDTH;
    private static int HEIGHT;
    int x, y;
    int width, height;

    public Room(TETile[][] world, int x, int y, int w, int h) {
        door = new PointSet();
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

        int midX = x + width / 2;
        int midY = y + height / 2;

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
