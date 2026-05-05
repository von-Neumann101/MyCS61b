package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;

import static core.World.buildWorld;

public class Main {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private static final String MAX_SEED = "9223372036854775807";

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();

        World w = getWorld();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(w.world);
        System.out.println("awa");
    }

    private static World getWorld() {
        drawMainMenu();

        while(true) {
            while (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                c = Character.toLowerCase(c);

                switch (c) {
                    case 'n':
                        long seed = getSeedFromUser();
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

    /**
     * 绘制Seed输入界面
     * @param seed 输入的种子
     */
    private static void drawSeedScreen(String seed, String message) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);

        double centerX = WIDTH / 2.0;

        StdDraw.setFont(new Font("Monospaced", Font.BOLD, 32));
        StdDraw.text(centerX, HEIGHT * 0.72, "Enter Seed");

        StdDraw.setFont(new Font("Monospaced", Font.BOLD, 24));
        StdDraw.text(centerX, HEIGHT * 0.55, seed + "_");

        StdDraw.setFont(new Font("Monospaced", Font.PLAIN, 18));
        StdDraw.text(centerX, HEIGHT * 0.40, "Press S to start");

        if (!message.isEmpty()) {
            StdDraw.setFont(new Font("Monospaced", Font.PLAIN, 16));
            StdDraw.text(centerX, HEIGHT * 0.30, message);
        }

        StdDraw.show();
    }

    private static World loadWorld() {
        return null;
    }

    /**
     * 从用户输入得到种子，并返回
     * @return 用户输入的种子
     */
    private static long getSeedFromUser() {
        String seed = "";
        drawSeedScreen(seed, "");

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();

                if (Character.isDigit(c)) {
                    String nextSeed = seed + c;

                    if (isValidSeedString(nextSeed)) {
                        seed = nextSeed;
                        drawSeedScreen(seed, "");
                    } else {
                        drawSeedScreen(seed, "Seed must be <= " + MAX_SEED);
                    }

                } else if (c == 's' || c == 'S') {
                    if (seed.isEmpty()) {
                        drawSeedScreen(seed, "Seed cannot be empty");
                    } else if (isValidSeedString(seed)) {
                        String normalizedSeed = stripLeadingZeros(seed);
                        return Long.parseLong(normalizedSeed);
                    } else {
                        drawSeedScreen(seed, "Invalid seed");
                    }

                } else if (c == '\b') {
                    if (!seed.isEmpty()) {
                        seed = seed.substring(0, seed.length() - 1);
                        drawSeedScreen(seed, "");
                    }
                }
            }

            StdDraw.pause(10);
        }
    }

    private static boolean isValidSeedString(String seed) {
        if (seed == null || seed.isEmpty()) {
            return false;
        }

        // 去掉前导 0，例如 "000123" -> "123"
        seed = stripLeadingZeros(seed);

        if (seed.length() < MAX_SEED.length()) {
            return true;
        }

        if (seed.length() > MAX_SEED.length()) {
            return false;
        }

        return seed.compareTo(MAX_SEED) <= 0;
    }

    private static String stripLeadingZeros(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }

        if (i == s.length()) {
            return "0";
        }

        return s.substring(i);
    }
}
