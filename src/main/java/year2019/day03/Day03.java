package year2019.day03;

import common.Pair;
import common.StringOps;
import common.grid.*;
import common.runners.Input;
import common.runners.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day03 extends Solution<Integer> {

    public Day03() {
        super(2019, 3);
    }

    private List<Segment> toSegments(String line) {
        var segments = new ArrayList<Segment>();
        var position = Point.ORIGIN;
        var directions  = StringOps.asList(line, ",");
        for (var direction : directions) {
            var vector = new Vector(position,
                    Direction.fromString(direction.substring(0, 1)),
                    Integer.parseInt(direction.substring(1)));
            var segment = vector.toSegment();
            segments.add(segment);
            position = segment.end();
        }
        return segments;
    }

    private Point findCrossing(List<Segment> line1, List<Segment> line2) {
        var grid = new InfiniteGrid<>(() -> new Pair<>(false, false));
        line1.stream().map(Segment::getPoints).flatMap(List::stream).forEach(point -> grid.get(point).setFirst(true));
        line2.stream().map(Segment::getPoints).flatMap(List::stream).forEach(point -> grid.get(point).setSecond(true));
        return grid.entrySet().stream()
                .filter(e -> e.getValue().first() && e.getValue().second())
                .map(Map.Entry::getKey)
                .sorted()
                .toList()
                .get(1); // First element is origin (0, 0)
    }

    @Override
    public Integer partOne(Input input) {
        var segments = input.asList().stream().map(this::toSegments).toList();
        return findCrossing(segments.get(0), segments.get(1)).manhattanDistance();
    }

    @Override
    public Integer partTwo(Input input) {
        return 0;
    }
}
