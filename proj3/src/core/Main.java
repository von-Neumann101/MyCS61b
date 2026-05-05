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


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();

        World w = getWorld();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(w.world);

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
