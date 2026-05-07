package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.List;

import static core.Main.unchanged_world;
import static core.Room.randomValidPosition;
import static core.World.*;

public class Portal {
    Room location;
    Portal link;
    Point position;
    boolean isLinked;

    Portal(Room r) {
        location = r;
        link = p;
        position = randomValidPosition(location, rand);
    }

    public void portal(TETile[][] world, Point player) {
        Point destination =  link.position;
        world[destination.x][destination.y] = Tileset.AVATAR;
        world[player.x][player.y] = unchanged_world[player.x][player.y];

        player.x = destination.x;
        player.y = destination.y;
    }

    public static void linkPortal(Portal p1, Portal p2) {
        if (p1.isLinked || p2.isLinked) {
            return;
        }
        p1.link = p2;
        p2.link = p1;
        p1.isLinked = true;
        p2.isLinked = true;
    }

    public static Portal firstPortal(World world) {
        List<Room> rooms_ = copyRooms(world.rooms);
        Room user_room = rooms_.getLast();
        Room other_room = rooms_.get(rooms_.size() - 2);

        Portal first_portal = new Portal(user_room);
        Portal other_portal = new Portal(other_room);

        linkPortal(first_portal, other_portal);
        return first_portal;
    }

    public static class Pair {
        public Point a;
        public Point b;

        public Pair(Point a, Point b) {
            this.a = a;
            this.b = b;
        }

        public Point other(Point p) {
            if (p.equals(a)) return b;
            if (p.equals(b)) return a;

            throw new IllegalArgumentException();
        }
    }
}
