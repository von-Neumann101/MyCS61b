package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;

import static core.Menu.drawMainMenu;
import static core.Menu.getSeedFromUser;
import static core.World.buildWorld;

public class Main {

    static final int WIDTH = 60;
    static final int HEIGHT = 30;
    static final long PLAY_SEED = 437976466;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();

        World w = getWorld();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(w.world);

        Point user = w.userInitialPosition();
        world[user.x][user.y] = Tileset.AVATAR;
        while (true) {// 持续渲染
            while (StdDraw.hasNextKeyTyped()) {
                char c = Character.toLowerCase(StdDraw.nextKeyTyped());

                if (exit) {
                    if (c == 'q') {
                        System.exit(0);
                    } else {
                        continue;
                    }
                }

                switch (c) {
                    case 'a':
                        if (world[user.x - 1][user.y] == Tileset.WALL) break;
                        world[user.x - 1][user.y] = Tileset.AVATAR;
                        world[user.x][user.y] = Tileset.FLOOR;
                        user.x -= 1;
                        break;
                    case 'w':
                        if (world[user.x][user.y + 1] == Tileset.WALL) break;
                        world[user.x][user.y + 1] = Tileset.AVATAR;
                        world[user.x][user.y] = Tileset.FLOOR;
                        user.y += 1;
                        break;
                    case 's':
                        if (world[user.x][user.y - 1] == Tileset.WALL) break;
                        world[user.x][user.y - 1] = Tileset.AVATAR;
                        world[user.x][user.y] = Tileset.FLOOR;
                        user.y -= 1;
                        break;
                    case 'd':
                        if (world[user.x + 1][user.y] == Tileset.WALL) break;
                        world[user.x + 1][user.y] = Tileset.AVATAR;
                        world[user.x][user.y] = Tileset.FLOOR;
                        user.x += 1;
                        break;
                    case ':':
                        exit = true;
                        break;
                    default:
                        break;
                }
            }
            ter.renderFrame(world);
            drawHUD(world);
            StdDraw.show();
            StdDraw.pause(2);
        }
    }

    private static World getWorld() {
        drawMainMenu(WIDTH, HEIGHT);

        while(true) {
            while (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                c = Character.toLowerCase(c);

                switch (c) {
                    case 'n':
                        long seed = Menu.getSeedFromUser();
                        return buildWorld(WIDTH, HEIGHT, seed);
                    case 'l':
                        return loadWorld();
                    case 'q':
                        System.exit(0);
                    default:
                        break;
                }
            }
        }
    }

    private static World loadWorld() {
        return null;
    }

}
