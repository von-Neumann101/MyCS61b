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

    private void addRoom() {
        int width = RandomUtils.uniform(rand, 2, 5);
        int height = RandomUtils.uniform(rand, 3, 7);
        int X = RandomUtils.uniform(rand, 0, WIDTH);
        int Y = RandomUtils.uniform(rand, 0, HEIGHT);
        drawRectangle(X, Y, width, height);
    }

    private void drawRectangle(int startX, int startY, int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i + startX >= WIDTH || startY - j <= -1) {
                    continue;
                }
                this.world[i + startX][startY - j] = WALL;
            }
        }
    }
}
