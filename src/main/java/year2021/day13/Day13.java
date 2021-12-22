package year2021.day13;

import common.grid.Point;
import common.input.Input;
import common.input.InputGroup;
import common.runners.Solution;
import io.vavr.collection.List;
import io.vavr.collection.Set;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public class Day13 extends Solution<Integer> {

    public Day13() {
        super(2021, 13);
    }

    private Set<Point> horizontalFold(Set<Point> points, int y) {
        return points.map(p -> (p.y() < y) ? p : new Point(p.x(), 2 * y - p.y()));
    }

    private Set<Point> verticalFold(Set<Point> points, int x) {
        return points.map(p -> (p.x() < x) ? p : new Point(2 * x - p.x(), p.y()));
    }

    public List<Function<Set<Point>, Set<Point>>> toFolds(InputGroup input) {
        return input.asList()
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
        var points = groups.get(0).asSet(Point::fromString);
        var folds = toFolds(groups.get(1));

        return folds.head().apply(points).size();
    }

    @Override
    public Integer partTwo(Input input) {
        var groups = input.asGroups();
        var points = groups.get(0).asSet(Point::fromString);
        var folds = toFolds(groups.get(1));

        for (var fold : folds) {
            points = fold.apply(points);
        }

        System.out.println(Point.render(points));
        return null;
    }

}
