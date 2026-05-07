package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
import java.util.Objects;

import static core.Menu.drawMainMenu;
import static core.World.buildWorld;
import static core.World.copyWorld;

public class Main {
    static final int WIDTH = 80;
    static final int HEIGHT = 55;

    static long WORLD_GENERATE_SEED;
    static boolean isLoad = false;
    static TERenderer ter = new TERenderer();
    static World w = getWorld();
    static TETile[][] world = w.world;
    final static TETile[][] unchanged_world = copyWorld(world);
    public static void main(String[] args) {
        Point user_position = initiateUserPosition(w);
        Entity entity1 = new Entity(initiateEntityPosition(w));
        world[user_position.x][user_position.y] = Tileset.AVATAR;
        world[entity1.position.x][entity1.position.y] = Tileset.ENTITY;

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
                        SaveLoad.put("entity_x", entity1.position.x+"");
                        SaveLoad.put("entity_y", entity1.position.y+"");
                        System.exit(0);
                    } else {
                        continue;
                    }
                }

                switch (c) {
                    case 'a':
                        if (world[user_position.x - 1][user_position.y] == Tileset.WALL) break;
                        world[user_position.x - 1][user_position.y] = Tileset.AVATAR;
                        world[user_position.x][user_position.y] = unchanged_world[user_position.x][user_position.y];
                        user_position.x -= 1;
                        entity1.moveEntity(user_position, w);
                        break;
                    case 'w':
                        if (world[user_position.x][user_position.y + 1] == Tileset.WALL) break;
                        world[user_position.x][user_position.y + 1] = Tileset.AVATAR;
                        world[user_position.x][user_position.y] = unchanged_world[user_position.x][user_position.y];
                        user_position.y += 1;
                        entity1.moveEntity(user_position, w);
                        break;
                    case 's':
                        if (world[user_position.x][user_position.y - 1] == Tileset.WALL) break;
                        world[user_position.x][user_position.y - 1] = Tileset.AVATAR;
                        world[user_position.x][user_position.y] = unchanged_world[user_position.x][user_position.y];
                        user_position.y -= 1;
                        entity1.moveEntity(user_position, w);
                        break;
                    case 'd':
                        if (world[user_position.x + 1][user_position.y] == Tileset.WALL) break;
                        world[user_position.x + 1][user_position.y] = Tileset.AVATAR;
                        world[user_position.x][user_position.y] = unchanged_world[user_position.x][user_position.y];
                        user_position.x += 1;
                        entity1.moveEntity(user_position, w);
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

    public static void gameOver() {
        ter.initialize(WIDTH, HEIGHT);

        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);

        double centerX = WIDTH / 2.0;
        double centerY = HEIGHT / 2.0;

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(new Font("Monospaced", Font.BOLD, 40));
        StdDraw.text(centerX, centerY + 1, "Game Over");

        StdDraw.show();
        StdDraw.pause(20000);
        System.exit(0);
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

    private static Point initiateUserPosition(World w) {
        if (isLoad) { //是否加载
            // 是——读取上次位置
            int x = Integer.parseInt(SaveLoad.get("position_x"));
            int y = Integer.parseInt(SaveLoad.get("position_y"));
            return new Point(x, y);
        } else {
            // 否——一般初始化
            return w.userInitialPosition();
        }
    }

    private static Point initiateEntityPosition(World w) {
        if (isLoad) { //是否加载
            // 是——读取上次位置
            int x = Integer.parseInt(SaveLoad.get("entity_x"));
            int y = Integer.parseInt(SaveLoad.get("entity_y"));
            return new Point(x, y);
        } else {
            // 否——一般初始化
            return w.entityInitialPosition();
        }
    }
}
