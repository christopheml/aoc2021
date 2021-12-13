package common.grid;

import io.vavr.collection.Traversable;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import static java.lang.Integer.parseInt;
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

    public static Point fromString(String text) {
        var parts = text.split(",");
        return new Point(parseInt(parts[0]), parseInt(parts[1]));
    }

    public static String render(Traversable<Point> points) {
        var edge = new Point(points.maxBy(Point::x).get().x() + 1, points.maxBy(Point::y).get().y() + 1);
        var sb = new StringBuilder();
        for (int y = 0; y < edge.y(); ++y) {
            for (int x = 0; x < edge.x(); ++x) {
                sb.append(points.contains(new Point(x, y)) ? "#" : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
