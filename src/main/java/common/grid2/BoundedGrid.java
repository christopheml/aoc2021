package common.grid2;

import common.grid.Point;
import io.vavr.collection.Stream;

abstract class BoundedGrid {

    protected final int width;
    protected final int height;

    protected BoundedGrid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private boolean inBounds(Point position) {
        return position.x() >= 0 && position.x() < width && position.y() >= 0 && position.y() < height;
    }

    protected void boundsCheck(Point position) {
        if (!inBounds(position)) throw new IllegalArgumentException("Point " + position + " is outside bounds");
    }

    public Stream<Point> positions() {
        return Stream.range(0, height).flatMap(y -> Stream.range(0, width).map(x -> new Point(x, y)));
    }

    public Stream<Point> directNeighborPositions(Point position) {
        return Stream.of(
                position.move(-1, 0),
                position.move(0, -1),
                position.move(0, 1),
                position.move(1, 0)
        ).filter(this::inBounds);
    }

    public Stream<Point> allNeighborPositions(Point position) {
        return Stream.of(
                position.move(-1, -1), position.move(-1, 0), position.move(-1, 1),
                position.move(0, -1), position.move(0, 1),
                position.move(1, -1), position.move(1, 0), position.move(1, 1)
        ).filter(this::inBounds);
    }

}
