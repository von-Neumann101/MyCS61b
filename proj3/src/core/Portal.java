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

    public void portal() {
        Room destination = link.location;

    }
}
