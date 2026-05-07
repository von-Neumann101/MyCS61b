package core;

import tileengine.TETile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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

    public Point randomValidPosition(Random random) {
        List<Point> candidates = new ArrayList<>();

        for (int i = x + 1; i < x + width - 1; i++) {
            for (int j = y + 1; j < y + height - 1; j++) {
                if (isValidPosition(i, j)) {
                    candidates.add(new Point(i, j));
                }
            }
        }

        if (candidates.isEmpty()) {
            throw new IllegalStateException("No valid position in this room.");
        }

        return candidates.get(random.nextInt(candidates.size()));
    }

    private boolean isValidPosition(int px, int py) {
        if (world[px][py] != FLOOR) {
            return false;
        }

        return !hasWallFloorWall(px, py);
    }

    private boolean isWall(int px, int py) {
        if (px < 0 || px >= WIDTH || py < 0 || py >= HEIGHT) {
            return false;
        }

        return world[px][py] == WALL;
    }
}
