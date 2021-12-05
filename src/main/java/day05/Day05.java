package day05;

import common.*;

import java.util.List;

import static java.lang.Integer.*;

public class Day05 {


    private static Segment toSegment(String line) {
        var points = line.split(" -> ");
        return new Segment(toPoint(points[0]), toPoint(points[1]));
    }

    private static Point toPoint(String coords) {
        var values = coords.split(",");
        return new Point(parseInt(values[0]), parseInt(values[1]));
    }

    public static Long partOne(Input input) {
        var segments = input.asList(Day05::toSegment);
        var grid = new InfiniteGrid<>(Counter::new);

        segments.stream()
                .filter(Segment::isStraight)
                .map(Segment::getPoints)
                .flatMap(List::stream)
                .forEach(point -> grid.get(point).inc());

        return grid.values().stream().filter(c -> c.get() > 1).count();
    }

    public static Long partTwo(Input input) {
        var segments = input.asList(Day05::toSegment);
        var grid = new InfiniteGrid<>(Counter::new);

        segments.stream()
                .map(Segment::getPoints)
                .flatMap(List::stream)
                .forEach(point -> grid.get(point).inc());

        return grid.values().stream().filter(c -> c.get() > 1).count();
    }

    public static void main(String[] args) {
        new Solution<>(5, Day05::partOne, Day05::partTwo).run();
    }

}
