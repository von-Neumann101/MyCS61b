package core;

public class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Point p) {
            if (p.x == this.x && p.y == this.y) {
                return true;
            }
        }
        return false;
    }
}
