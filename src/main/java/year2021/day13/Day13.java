package year2021.day13;

import common.grid.Point;
import common.runners.Input;
import common.runners.Solution;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;

import java.util.Collection;
import java.util.function.Function;

import static java.lang.Integer.parseInt;

public class Day13 extends Solution<Integer> {

    public Day13() {
        super(2021, 13);
    }

    private Set<Point> horizontalFold(Set<Point> points, int y) {
        return points.map(p -> {
            if (p.y() < y) return p;
            return new Point(p.x(), 2 * y - p.y());
        });
    }

    private Set<Point> verticalFold(Set<Point> points, int x) {
        return points.map(p -> {
            if (p.x() < x) return p;
            return new Point(2 * x - p.x(), p.y());
        });
    }

    public List<Function<Set<Point>, Set<Point>>> toFolds(Collection<String> lines) {
        return List.ofAll(lines)
                .map(line -> {
                    var parts = line.split("=");
                    var position = parseInt(parts[1]);
                    return "fold along x".equals(parts[0]) ?
                            (points) -> verticalFold(points, position) :
                            (points) -> horizontalFold(points, position);
                });
    }

    @Override
    public Integer partOne(Input input) {
        var groups = input.asGroups();
        var points = HashSet.ofAll(groups.get(0)).map(Point::fromString);
        var folds = toFolds(groups.get(1));

        return folds.head().apply(points).size();
    }

    @Override
    public Integer partTwo(Input input) {
        var groups = input.asGroups();
        Set<Point> points = HashSet.ofAll(groups.get(0)).map(Point::fromString);
        var folds = toFolds(groups.get(1));

        for (var fold : folds) {
            points = fold.apply(points);
        }

        System.out.println(Point.render(points));
        return null;
    }

}
