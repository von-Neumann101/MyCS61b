package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.Objects;

import static core.Menu.drawMainMenu;
import static core.World.buildWorld;

public class Main {
    static final int WIDTH = 60;
    static final int HEIGHT = 30;
    static final long PLAY_SEED = 437976466;
    static long WORLD_GENERATE_SEED;
    static boolean isLoad = false;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();

        World w = getWorld();
        TETile[][] world = w.world;

        Point user_position = initiateUserPosition(w);
        
        world[user_position.x][user_position.y] = Tileset.AVATAR;

        boolean exit = false;

        ter.initialize(WIDTH, HEIGHT + 2);//高度+1给HUD留空
        while (true) {// 持续渲染

            while (StdDraw.hasNextKeyTyped()) {
                char c = Character.toLowerCase(StdDraw.nextKeyTyped());

                if (exit) {
                    if (c == 'q') {
                        SaveLoad.put("world_generate_seed", WORLD_GENERATE_SEED+"");
                        SaveLoad.put("position_x", user_position.x+"");
                        SaveLoad.put("position_y", user_position.y+"");
                        System.exit(0);
                    } else {
                        continue;
                    }
                }

                switch (c) {
                    case 'a':
                        if (world[user_position.x - 1][user_position.y] == Tileset.WALL) break;
                        world[user_position.x - 1][user_position.y] = Tileset.AVATAR;
                        world[user_position.x][user_position.y] = Tileset.FLOOR;
                        user_position.x -= 1;
                        break;
                    case 'w':
                        if (world[user_position.x][user_position.y + 1] == Tileset.WALL) break;
                        world[user_position.x][user_position.y + 1] = Tileset.AVATAR;
                        world[user_position.x][user_position.y] = Tileset.FLOOR;
                        user_position.y += 1;
                        break;
                    case 's':
                        if (world[user_position.x][user_position.y - 1] == Tileset.WALL) break;
                        world[user_position.x][user_position.y - 1] = Tileset.AVATAR;
                        world[user_position.x][user_position.y] = Tileset.FLOOR;
                        user_position.y -= 1;
                        break;
                    case 'd':
                        if (world[user_position.x + 1][user_position.y] == Tileset.WALL) break;
                        world[user_position.x + 1][user_position.y] = Tileset.AVATAR;
                        world[user_position.x][user_position.y] = Tileset.FLOOR;
                        user_position.x += 1;
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
            StdDraw.pause(20);
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
                        WORLD_GENERATE_SEED = Menu.getSeedFromUser();
                        return buildWorld(WIDTH, HEIGHT, WORLD_GENERATE_SEED);
                    case 'l':
                        if (SaveLoad.fileMissingOrEmpty()) {
                            StdDraw.text(WIDTH / 2.0, HEIGHT * 0.1, "No Archive");
                            StdDraw.show();
                            break;
                        }
                        isLoad = true; //状态机
                        long world_generate_seed = Long.parseLong(SaveLoad.get("world_generate_seed"));
                        return buildWorld(WIDTH, HEIGHT, world_generate_seed);
                    case 'q':
                        System.exit(0);
                    default:
                        break;
                }
            }
        }
    }

    private static void drawHUD(TETile[][] world) {
        int x = (int) StdDraw.mouseX(),
                y = (int) StdDraw.mouseY();
        String HUD = "";

        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            HUD = world[x][y].description();
            if (Objects.equals(HUD, Tileset.NOTHING.description())) {
                HUD = "";
            }
        }
        StdDraw.setPenColor(255, 255, 255);
        StdDraw.textLeft(2, HEIGHT + 1, HUD);
    }

}
