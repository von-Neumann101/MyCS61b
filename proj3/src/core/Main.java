package core;

public class Main {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(HEIGHT, WIDTH);
        TETile[][] menu = new TETile[HEIGHT][WIDTH];
        initMenu(menu);
        ter.renderFrame(menu);
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

    private static void initMenu(TETile[][] world) {
        int x = world.length;
        int y = world[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

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
}
