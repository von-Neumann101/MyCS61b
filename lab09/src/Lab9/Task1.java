package Lab9;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world initially full of trees.
 */
public class Task1 {
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

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(30, 20);

        TETile[][] world = new TETile[30][15];

        fillWithTrees(world);

        ter.renderFrame(world);
    }
}