package common;

public record Point(int x, int y) {

    public static final Point ORIGIN = new Point(0, 0);

    public Point add(Point other) {
        return new Point(x + other.x, y + other.y);
    }

}
