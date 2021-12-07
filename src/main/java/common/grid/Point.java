package common.grid;

import static java.lang.Math.abs;

public record Point(int x, int y) implements Comparable<Point> {

    public static final Point ORIGIN = new Point(0, 0);

    public int manhattanDistance() {
        return abs(x) + abs(y);
    }

    @Override
    public int compareTo(Point o) {
        return this.manhattanDistance() - o.manhattanDistance();
    }
}
