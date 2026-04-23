package core;

public class World {
    static int WIDTH;
    static int HEIGHT;
    TETile[][] world;
    
    public World(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        world = new TETile[width][height];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

}
