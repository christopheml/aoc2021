package year2021.day13;

import common.grid.Point;
import common.runners.Input;
import common.runners.Solution;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;

import static java.lang.Integer.parseInt;

public class Day13 extends Solution<Integer> {

    public Day13() {
        super(2021, 13);
    }

    private static sealed abstract class Fold {
        public abstract Set<Point> apply(Set<Point> points);

        public static Fold parse(String text) {
            var parts = text.split("=");
            var position = parseInt(parts[1]);
            return "fold along x".equals(parts[0]) ? new VerticalFold(position) : new HorizontalFold(position);
        }
    }

    private final static class HorizontalFold extends Fold {

        private final int y;

        public HorizontalFold(int y) {
            this.y = y;
        }

        @Override
        public Set<Point> apply(Set<Point> points) {
            return points.map(p -> {
                if (p.y() < y) return p;
                return new Point(p.x(), 2 * y - p.y());
            });
        }
    }

    private final static class VerticalFold extends Fold {

        private final int x;

        public VerticalFold(int x) {
            this.x = x;
        }

        @Override
        public Set<Point> apply(Set<Point> points) {
            return points.map(p -> {
                if (p.x() < x) return p;
                return new Point(2 * x - p.x(), p.y());
            });
        }
    }

    @Override
    public Integer partOne(Input input) {
        var groups = input.asGroups();
        var points = HashSet.ofAll(groups.get(0)).map(Point::fromString);
        var folds = List.ofAll(groups.get(1)).map(Fold::parse);

        return folds.head().apply(points).size();
    }

    @Override
    public Integer partTwo(Input input) {
        var groups = input.asGroups();
        Set<Point> points = HashSet.ofAll(groups.get(0)).map(Point::fromString);
        var folds = List.ofAll(groups.get(1)).map(Fold::parse);

        for (var fold : folds) {
            points = fold.apply(points);
        }
        var edge = points.maxBy(Point::manhattanDistance).get();
        var sb = new StringBuilder();
        for (int y = 0; y < edge.y() + 2; ++y) {
            for (int x = 0; x < edge.x() + 2; ++x) {
                sb.append(points.contains(new Point(x, y)) ? "#" : " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        return null;
    }

}
