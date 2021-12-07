package common.grid;

public record Vector(Point origin, Direction direction, int size) {

    public Point end() {
        return switch (direction) {
            case UP -> new Point(origin.x(), origin.y() + size);
            case DOWN -> new Point(origin.x(), origin.y() - size);
            case LEFT -> new Point(origin.x() - size, origin.y());
            case RIGHT -> new Point(origin.x() + size, origin.y());
        };
    }

    public Segment toSegment() {
        return new Segment(origin, end());
    }

}
