package common.grid;

public record Vector(Direction direction, int distance) {

    public Point toPoint(Point origin) {
        return apply(origin, direction, distance);
    }

    public Segment toSegment(Point origin) {
        return new Segment(origin, toPoint(origin));
    }

    public Segment extend(Segment segment) {
        return toSegment(segment.end());
    }

    private Point apply(Point origin, Direction direction, int distance) {
        return switch (direction) {
            case UP -> new Point(origin.x(), origin.y() + distance);
            case DOWN -> new Point(origin.x(), origin.y() - distance);
            case LEFT -> new Point(origin.x() - distance, origin.y());
            case RIGHT -> new Point(origin.x() + distance, origin.y());
        };
    }

}
