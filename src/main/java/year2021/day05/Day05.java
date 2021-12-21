package year2021.day05;

import common.Counter;
import common.grid.InfiniteGrid;
import common.grid.Point;
import common.grid.Segment;
import common.input.Input;
import common.runners.Solution;

import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

public class Day05 extends Solution<Long> {

    public Day05() {
        super(2021, 5);
    }

    private Segment toSegment(String line) {
        var points = line.split(" -> ");
        return new Segment(toPoint(points[0]), toPoint(points[1]));
    }

    private Point toPoint(String coords) {
        var values = coords.split(",");
        return new Point(parseInt(values[0]), parseInt(values[1]));
    }

    private long countIntersections(Input input, Predicate<Segment> keepSegment) {
        var segments = input.asList(this::toSegment);
        var grid = new InfiniteGrid<>(Counter::new);

        segments.filter(keepSegment)
                .flatMap(Segment::getPoints)
                .forEach(point -> grid.get(point).inc());

        return grid.values().stream().filter(c -> c.get() > 1).count();
    }

    public Long partOne(Input input) {
        return countIntersections(input, Segment::isStraight);
    }

    public Long partTwo(Input input) {
        return countIntersections(input, s -> true);
    }

}
