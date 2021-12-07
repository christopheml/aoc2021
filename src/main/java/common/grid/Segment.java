package common.grid;

import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Integer.max;
import static java.lang.Math.abs;

public record Segment(Point origin, Point end) {

    public boolean isHorizontal() {
        return origin.y() == end.y();
    }

    public boolean isVertical() {
        return origin.x() == end.x();
    }

    public boolean isStraight() {
        return isHorizontal() || isVertical();
    }

    public List<Point> getPoints() {
        var xIncrement = isVertical() ? 0 : (origin.x() < end.x() ? 1 : - 1);
        var yIncrement = isHorizontal() ? 0 : (origin.y() < end.y() ? 1 : - 1);
        var length = max(abs(end.y() - origin.y()), abs(end.x() - origin.x()));
        return IntStream.rangeClosed(0, length)
                .mapToObj(i -> new Point(origin.x() + i * xIncrement, origin.y() + i * yIncrement))
                .toList();
    }

    public int length() {
        if (isHorizontal()) return Math.abs(end.x() - origin.x());
        if (isVertical()) return Math.abs(end.y() - origin.y());
        throw new IllegalArgumentException("Segment not straight");
    }

}
