package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;

import static core.World.buildWorld;

public class Main {

    private static final int HEIGHT = 60;
    private static final int WIDTH = 30;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(HEIGHT, WIDTH);
        drawMainMenu();
        while(true) {
            while (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                c = Character.toLowerCase(c);
                switch (c) {
                    case 'n':
                        break;
                    case 'l':
                        break;
                    case 'q':
                        break;
                }
            }
        }
        
        World w = buildWorld(60, 30);
        ter.renderFrame(w.world);
        System.out.println("awa");
    }

    /**
     * 绘制Menu菜单
     */
    private static void drawMainMenu() {
        // 让 StdDraw 坐标和窗口像素大小一致
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);

        StdDraw.enableDoubleBuffering();

        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);

        double centerX = WIDTH / 2.0;

        StdDraw.setFont(new Font("Monospaced", Font.BOLD, 36));
        StdDraw.text(centerX, HEIGHT * 0.85, "CS61B: BYOW");

        StdDraw.setFont(new Font("Monospaced", Font.BOLD, 28));
        StdDraw.text(centerX, HEIGHT * 0.62, "(N) New Game");
        StdDraw.text(centerX, HEIGHT * 0.49, "(L) Load Game");
        StdDraw.text(centerX, HEIGHT * 0.36, "(Q) Quit Game");

        StdDraw.show();
    }

    private static World getWorld() {
        drawMainMenu();

        while(true) {
            while (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                c = Character.toLowerCase(c);

                switch (c) {
                    case 'n':
                        break;
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
