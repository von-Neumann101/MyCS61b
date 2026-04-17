package Lab9;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.util.Random;

/**
 * Draws a world initially full of trees.
 */
public class Task2 {
    private final static int WORLD_WEIGHT = 30;
    private final static int WORLD_HEIGHT = 15;
    private static final long SEED = 437976466;
    /**
     * Fills the entire 2D world with the Tileset.TREE tile.
     */
    private static void fillWithTrees(TETile[][] world) {
        int x = world.length;
        int y = world[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                world[i][j] = Tileset.TREE;
            }
        }
    }
    private static void drawSquare(TETile[][] world, int startX, int startY, int size, TETile tile) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i + startX >= WORLD_WEIGHT || startY - j <= -1) {
                    continue;
                }
                world[i + startX][startY - j] = tile;
            }
        }
    }

    private static void addRandomSquare(TETile[][] world, Random rand) {
        int size = RandomUtils.uniform(rand, 3, 8);
        int X = RandomUtils.uniform(rand, 0, WORLD_WEIGHT);
        int Y = RandomUtils.uniform(rand, 0, WORLD_HEIGHT);
        drawSquare(world, X, Y, size, Tileset.FLOWER);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(30, 20);

        TETile[][] world = new TETile[WORLD_WEIGHT][WORLD_HEIGHT];

        fillWithTrees(world);
        addRandomSquare(world, new Random(SEED));

        ter.renderFrame(world);
    }
}