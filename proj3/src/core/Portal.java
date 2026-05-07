package core;

public class Portal {
    Room location;
    Portal link;
    Point position;
    static List<Room> rooms_ = copyRooms(rooms);
    
    Portal(Room r, Portal p) {
        location = r;
        link = p;
        position = randomValidPosition(location, rand);
    }

    public void portal(TETile[][] world) {
        Point destination =  link.position;
        world[destination.x][destination.y] = Tileset.AVATAR;
    }

    public void linkPortal(Portal p1, Portal p2) {
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
}
