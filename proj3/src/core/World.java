package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.util.Random;

import static tileengine.Tileset.FLOOR;
import static tileengine.Tileset.WALL;

public class World {
    private static final long SEED = 437976466;
    Random rand;
    static int WIDTH;
    static int HEIGHT;
    TETile[][] world;
    List<Room> rooms;

    public World(int width, int height) {
        rand = new Random(SEED);
        WIDTH = width;
        HEIGHT = height;
        world = new TETile[width][height];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    private void addRoom() {
        Room room = new Room(world);
        int width = RandomUtils.uniform(rand, 3, 5);
        int height = RandomUtils.uniform(rand, 3, 7);
        int X = RandomUtils.uniform(rand, 0, WIDTH);
        int Y = RandomUtils.uniform(rand, 0, HEIGHT);
        Room room = new Room(world, X, Y, width, height);
        if (!isEmptyArea(room)) return;
        room.draw();
        rooms.add(room);
    }

    private void generatePath() {
        List<Edge> mst = Graph.MST(rooms); // 根据rooms生成最小生成树
        List<Edge> edges = buildGraph(rooms);
        for (Edge e : mst) {

        }
    }

    private void drawPath(Edge edge) {
        Room r1 = rooms.get(edge.a);
        Room r2 = rooms.get(edge.b);
        int x1 = r1.centerX(), x2 = r2.centerX();
        int y1 = r1.centerY(), y2 = r2.centerY();
        int dx = x1 - x2, dy = y1 - y2;

        if (dx < 0) {
            for (int i = 0; i < -dx; i++) {
                world[i + x1][y1] = FLOOR;
            }
        } else {
            for (int i = 0; i < dx; i++) {
                world[i + x2][y2] = FLOOR;
            }
        }

        if (dy < 0) {
            for (int i = 0; i < -dx; i++) {
                world[Math.max(x1, x2)][i + y1] = FLOOR;
            }
        } else {
            for (int i = 0; i < dx; i++) {
                world[Math.max(x1, x2)][i + y2] = FLOOR;
            }
        }
    }

    private boolean isEmptyArea(Room room) {
        int x = room.x, y = room.y;
        int width = room.width, height = room.height;
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

    public TETile[][] buildWorld() {
        return world;
    }


    public static void main (String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(60, 40);
        World w = new World(60, 50);

        ter.renderFrame(w.world);
    }
}
