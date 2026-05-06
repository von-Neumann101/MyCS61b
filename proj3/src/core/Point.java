package core;

public class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String pointToString() {
        return "(" + x + "," + y + ")";
    }

    public static Point stringToPoint(String s) {
        String inside = s.substring(1, s.length() - 1);
        String[] parts = inside.split(",");
        int parsedX = Integer.parseInt(parts[0]);
        int parsedY = Integer.parseInt(parts[1]);
        return new Point(parsedX, parsedY);
    }
}
