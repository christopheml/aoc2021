package year2019.day03;

import common.StringOps;
import common.grid.Direction;
import common.grid.Point;
import common.grid.Segment;
import common.runners.Input;
import common.runners.Solution;
import io.vavr.Tuple2;
import io.vavr.collection.Map;
import io.vavr.collection.Seq;
import io.vavr.collection.Vector;

import java.util.function.Function;

import static java.lang.Integer.parseInt;

public class Day03 extends Solution<Integer> {

    public Day03() {
        super(2019, 3);
    }

    private common.grid.Vector toVector(String text) {
        return new common.grid.Vector(Direction.fromString(text.substring(0, 1)), parseInt(text.substring(1)));
    }

    private io.vavr.collection.Seq<Segment> toSegments(String line) {
        var bootstrap = new Segment(Point.ORIGIN, Point.ORIGIN);
        return Vector.ofAll(StringOps.asList(line, ","))
                .map(this::toVector)
                .scanLeft(bootstrap, (segment, vector) -> vector.extend(segment))
                .filter(s -> !s.equals(bootstrap));
    }

    @Override
    public Integer partOne(Input input) {
        var segments = Vector.ofAll(input.asList()).map(this::toSegments);
        var points1 = segments.head().flatMap(Segment::getPoints).toSet();
        var points2 = segments.last().flatMap(Segment::getPoints).toSet();
        return points1.intersect(points2)
                .filter(Point.isNotOrigin())
                .min()
                .map(Point::manhattanDistance)
                .getOrElseThrow(IllegalStateException::new);
    }

    private Map<Point, Integer> calculateSteps(Seq<Segment> segments) {
        return segments.flatMap(Segment::getPointsExclusive)
                .scanLeft(new Tuple2<>(Point.ORIGIN, -1),
                        (lastPosition, point) -> new Tuple2<>(point, lastPosition._2 + 1))
                .toMap(Function.identity());
    }

    @Override
    public Integer partTwo(Input input) {
        var segments = Vector.ofAll(input.asList()).map(this::toSegments);
        var steps1 = calculateSteps(segments.head());
        var steps2 = calculateSteps(segments.last());
        var intersections = steps1.keySet().intersect(steps2.keySet()).filter(Point.isNotOrigin());

        return intersections.map(p -> steps1.get(p).get() + steps2.get(p).get())
                .min()
                .getOrElseThrow(IllegalStateException::new);
    }
}
