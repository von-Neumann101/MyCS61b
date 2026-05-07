package core;

public class Portal {
    Room location;
    Portal link;

    Portal(Room r, Portal p) {
        location = r;
        link = p;
        position = randomValidPosition(location, rand);
    }

    public void portal() {
        Room destination = link.location;

    }
}
