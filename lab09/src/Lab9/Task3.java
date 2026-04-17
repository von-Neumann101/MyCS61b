package Lab9;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.util.Random;

/**
 * Draws a world initially full of trees.
 */
public class Task3 {
    private final static int WORLD_WEIGHT = 30;
    private final static int WORLD_HEIGHT = 15;
    private static final long SEED = 437976466;

    private static TETile randomTile(Random rand) {
        // The following call to nextInt() uses a bound of 3 (this is not a seed!) so
        // the result is bounded between 0, inclusive, and 3, exclusive. (0, 1, or 2)
        int tileNum = rand.nextInt(3);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            default -> Tileset.WATER;
        };
    }

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
        drawSquare(world, X, Y, size, randomTile(new Random(RandomUtils.uniform(rand, 0, 114514))));
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(30, 20);

        TETile[][] world = new TETile[WORLD_WEIGHT][WORLD_HEIGHT];

        fillWithTrees(world);
        char c;
        int SEED_offset = 0;
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                SEED_offset += 1;
                c = StdDraw.nextKeyTyped();
                c = Character.toLowerCase(c);
                switch (c) {
                    case 'n':
                        addRandomSquare(world, new Random(SEED + SEED_offset));
                        break;
                    case 'q':
                        System.exit(0); // Closes the game window and quits the game.
                        break;
                    default:
                        break;
                }
            }
            ter.renderFrame(world);
        }
    }
}