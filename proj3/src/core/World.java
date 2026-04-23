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

    /**
     * 绘制矩形
     * @param x 矩形左下角横坐标
     * @param y 矩形左下角纵坐标
     * @param width 宽
     * @param height 高
     */
    private void drawRectangle(int x, int y, int width, int height) {
        if (world[x][y] == WALL) return;
        if (x + width > WIDTH - 1) width = WIDTH - x;
        if (y + height > HEIGHT - 1) height = HEIGHT - y;
        if (width < 3 || height < 3) return;
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if (i == x || i == x + width - 1 || j == y || j == y + height - 1) {
                    world[i][j] = WALL;
                }
            }
        }
    }
}
