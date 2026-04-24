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
        room.draw();
        rooms.add(room);
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
