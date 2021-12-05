package day05;

import common.*;
import common.runners.Input;
import common.runners.Solution;

import java.util.List;

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

    public Long partOne(Input input) {
        var segments = input.asList(this::toSegment);
        var grid = new InfiniteGrid<>(Counter::new);

        segments.stream()
                .filter(Segment::isStraight)
                .map(Segment::getPoints)
                .flatMap(List::stream)
                .forEach(point -> grid.get(point).inc());

        return grid.values().stream().filter(c -> c.get() > 1).count();
    }

    public Long partTwo(Input input) {
        var segments = input.asList(this::toSegment);
        var grid = new InfiniteGrid<>(Counter::new);

        segments.stream()
                .map(Segment::getPoints)
                .flatMap(List::stream)
                .forEach(point -> grid.get(point).inc());

        return grid.values().stream().filter(c -> c.get() > 1).count();
    }

}
