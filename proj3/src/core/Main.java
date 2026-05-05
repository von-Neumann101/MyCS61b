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
}
