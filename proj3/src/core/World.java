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
        // 超出边界处理
        if (y + height > HEIGHT - 1) {
            for (int i = 0; i < width; i++) {
                if (i + x >= WIDTH) {
                    continue;
                }
                world[i][HEIGHT-1] = WALL;
            }
        }
        if (x + width > WIDTH - 1) {
            for (int j = 0; j < height; j++) {
                if (j + y >= HEIGHT) {
                    continue;
                }
                world[WIDTH-1][j] = WALL;
            }
        }
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
