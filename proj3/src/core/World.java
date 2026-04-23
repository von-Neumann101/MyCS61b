package core;

public class World {
    static int WIDTH;
    static int HEIGHT;
    TETile[][] world;
    
    public World(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        world = new TETile[width][height];
    }

}
