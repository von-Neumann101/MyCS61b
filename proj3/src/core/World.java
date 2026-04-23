package core;

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

    public TETile[][] buildWorld() {
        return world;
    }

    private static void generateRoom() {

    }

    private static void drawRectangle() {

    }
}
