package Lab9;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world initially full of trees.
 */
public class Task2 {
    private final static int WORLD_WEIGHT = 30;
    private final static int WORLD_HEIGHT = 15;

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
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(30, 20);

        TETile[][] world = new TETile[WORLD_WEIGHT][WORLD_HEIGHT];

        fillWithTrees(world);
        drawSquare(world, 10, 7, 5, Tileset.FLOWER);

        ter.renderFrame(world);
    }
}