package common.grid;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import static java.lang.Math.abs;

public record Point(int x, int y) implements Comparable<Point> {

    public static final Point ORIGIN = new Point(0, 0);

    public int manhattanDistance() {
        return abs(x) + abs(y);
    }

    public <T> T applyFunction(BiFunction<Integer, Integer, T> function) {
        return function.apply(x, y);
    }

    @Override
    public int compareTo(Point o) {
        return this.manhattanDistance() - o.manhattanDistance();
    }

    public static Predicate<Point> isOrigin() {
        return point -> point.equals(ORIGIN);
    }

    public static Predicate<Point> isNotOrigin() {
        return Predicate.not(isOrigin());
    }

    public Point move(int xOffset, int yOffset) {
        return new Point(x + xOffset, y + yOffset);
    }

}
