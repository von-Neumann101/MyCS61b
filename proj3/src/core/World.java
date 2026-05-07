package core;

import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import java.util.*;

import static core.Graph.buildGraph;
import static tileengine.Tileset.FLOOR;
import static tileengine.Tileset.WALL;

public class World {
    static long SEED;
    Random rand;
    static int WIDTH;
    static int HEIGHT;
    TETile[][] world;
    List<Room> rooms;

    public World(int width, int height, long seed) {
        SEED = seed;
        rand = new Random(seed);
        WIDTH = width;
        HEIGHT = height;
        world = new TETile[width][height];
        rooms = new ArrayList<>();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    private void addRoom() {
        int width = RandomUtils.uniform(rand, 5, 9);
        int height = RandomUtils.uniform(rand, 5, 9);
        int X = RandomUtils.uniform(rand, 0, WIDTH);
        int Y = RandomUtils.uniform(rand, 0, HEIGHT);
        Room room = new Room(world, X, Y, width, height);
        if (!isEmptyArea(room)) return;
        room.draw();
        rooms.add(room);
    }

    private void generatePath(List<Edge> mst) {
        for (Edge e : mst) {
            addPath(e);
        }
    }

    private void addPath(Edge edge) {
        Room r1 = rooms.get(edge.a);
        Room r2 = rooms.get(edge.b);
        int x1 = r1.centerX(), x2 = r2.centerX();
        int y1 = r1.centerY(), y2 = r2.centerY();
        int dx = x1 - x2, dy = y1 - y2;

        if (dx < 0) {
            for (int i = 0; i <= -dx; i++) {
                world[i + x1][y1] = FLOOR;
                if (world[i + x1][y1 + 1] != FLOOR) {
                    world[i + x1][y1 + 1] = WALL;
                }
                if (world[i + x1][y1 - 1] != FLOOR) {
                    world[i + x1][y1 - 1] = WALL;
                }
            }
        } else {
            for (int i = 0; i <= dx; i++) {
                world[i + x2][y2] = FLOOR;
                if (world[i + x2][y2 + 1] != FLOOR) {
                    world[i + x2][y2 + 1] = WALL;
                }
                if (world[i + x2][y2 - 1] != FLOOR) {
                    world[i + x2][y2 - 1] = WALL;
                }
            }
        }
        int x = Math.max(x1, x2);
        if (dy < 0) {
            for (int i = 0; i <= -dy; i++) {
                world[x][i + y1] = FLOOR;
                if (world[x - 1][i + y1] != FLOOR) {
                    world[x - 1][i + y1] = WALL;

                }
                if (world[x + 1][i + y1] != FLOOR) {
                    world[x + 1][i + y1] = WALL;
                }
            }
        } else {
            for (int i = 0; i <= dy; i++) {
                world[x][i + y2] = FLOOR;
                if (world[x - 1][i + y2] != FLOOR) {
                    world[x - 1][i + y2] = WALL;

                }
                if (world[x + 1][i + y2] != FLOOR) {
                    world[x + 1][i + y2] = WALL;
                }
            }
        }
    }

    private void addRandomPath(List<Edge> edges, List<Edge> mst) {
        Set<Edge> extra = new HashSet<>(edges);
        extra.removeAll(new HashSet<>(mst));

        List<Edge> candidates = new ArrayList<>(extra);

        int k = candidates.size() / 30;
        for (int i = 0; i < k; i++) {
            int j = i + rand.nextInt(candidates.size() - i);
            Collections.swap(candidates, i, j);

            Edge e = candidates.get(i);
            addPath(e);
        }
    }

    public static World buildWorld(int width, int height, long seed) {
        World w = new World(width, height, seed);
        for (int i = 0; i < 40; i++) {
            w.addRoom();
        }
        List<Edge> edges = buildGraph(w.rooms);
        List<Edge> mst = Graph.MST(w.rooms, edges);// 根据rooms生成最小生成树
        w.generatePath(mst);
        w.addRandomPath(edges, mst);
        return w;
    }


    private boolean isEmptyArea(Room room) {
        int x = room.x;
        int y = room.y;
        int width = room.width;
        int height = room.height;

        int W = world.length;
        int H = world[0].length;

        if (x < 0 || y < 0 || x + width > W || y + height > H) {
            return false;
        }

        if (width < 3 || height < 3) {
            return false;
        }

        int startX = Math.max(0, x - 1);
        int endX = Math.min(W - 1, x + width);
        int startY = Math.max(0, y - 1);
        int endY = Math.min(H - 1, y + height);

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (world[i][j] != Tileset.NOTHING) {
                    return false;
                }
            }
        }
        return true;
    }

    public Point userInitialPosition() {
        Room last_room = rooms.getLast();
        int room_x = last_room.centerX();
        int room_y = last_room.centerY();
        return new Point(room_x, room_y);
    }

    public Point entityInitialPosition() {
        Room last_room = rooms.getFirst();
        int room_x = last_room.centerX();
        int room_y = last_room.centerY();
        return new Point(room_x, room_y);
    }

    public boolean isWalkable(Point position) {
        int x = position.x;
        int y = position.y;

        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }
        return world[x][y] != WALL && world[x][y] != Tileset.NOTHING;
    }

    /**
     * 每个格点到玩家最近距离
     * @param player 玩家位置
     * @return 一个包含每个格点到玩家位置的距离的二维数组
     */
    public int[][] bfsDistanceFromUser(Point player) {
        int[][] dist = new int[WIDTH][HEIGHT]; // 到玩家的最短距离

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                dist[i][j] = -1; //-1为目前不可及
            }
        }

        Queue<Point> queue = new LinkedList<>();

        dist[player.x][player.y] = 0;
        queue.add(player);//从玩家处开始扩散

        int[][] direction = {
                {1, 0},//→
                {-1, 0},//←
                {0, 1},//↑
                {0, -1}//↓
        };

        while (!queue.isEmpty()) {
            Point cur = queue.remove();

            for (int [] dir : direction) { // 遍历各个方向
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];

                if (isWalkable(new Point(nx, ny)) && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[cur.x][cur.y] + 1;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        return dist;
    }


}
