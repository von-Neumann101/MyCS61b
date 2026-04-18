package Lab9;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Draws a world initially full of trees.
 */
public class Task4 {
    private final static int WORLD_WIDTH = 30;
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
                if (i + startX >= WORLD_WIDTH || startY - j <= -1) {
                    continue;
                }
                world[i + startX][startY - j] = tile;
            }
        }
    }

    private static void addRandomSquare(TETile[][] world, Random rand) {
        int size = RandomUtils.uniform(rand, 3, 8);
        int X = RandomUtils.uniform(rand, 0, WORLD_WIDTH);
        int Y = RandomUtils.uniform(rand, 0, WORLD_HEIGHT);
        drawSquare(world, X, Y, size, randomTile(rand));
    }

    private static void rebuild_world(TETile[][] world, List<Character> history) {
        fillWithTrees(world);
        Random rand = new Random(SEED);

        for (char op : history) {
            if (op == 'n') {
                addRandomSquare(world, rand);
            }
        }
    }

    private static Random rebuildRandom (List<Character> history) {
        Random rand = new Random(SEED);
        for (char op : history) {
            if (op == 'n') {
                RandomUtils.uniform(rand, 3, 8);
                RandomUtils.uniform(rand, 0, WORLD_WIDTH);
                RandomUtils.uniform(rand, 0, WORLD_HEIGHT);
                rand.nextInt(3);
            }
        }
        return rand;
    }

    private static void remove_square(List<Character> data) {
        if (!data.isEmpty()) data.removeLast();
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(30, 20);
        TETile[][] world = new TETile[WORLD_WIDTH][WORLD_HEIGHT];
        fillWithTrees(world);

        char c;
        Random rand = new Random(SEED);
        List<Character> build_history = new ArrayList<>();
        SaveLoad sl = new SaveLoad();

        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                c = StdDraw.nextKeyTyped();
                c = Character.toLowerCase(c);

                switch (c) {
                    case 'n':
                        addRandomSquare(world, rand);
                        build_history.add(c);
                        break;
                    case 's':
                        sl.save(build_history);
                        break;
                    case 'l':
                        world = new TETile[WORLD_WIDTH][WORLD_HEIGHT];
                        build_history = sl.load();
                        rebuild_world(world, sl.load());
                        rand = rebuildRandom(build_history);
                        break;
                    case 'd':
                        if (build_history.isEmpty()) break;
                        build_history.removeLast();
                        rebuild_world(world, build_history);
                        rand = rebuildRandom(build_history);
                        break;
                    case 'q':
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
            ter.renderFrame(world);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.textLeft(1, 17, "Number of squares: " + build_history.size());
            StdDraw.show();
            StdDraw.pause(2);
        }
    }
}