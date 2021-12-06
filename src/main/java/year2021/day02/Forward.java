package year2021.day02;

import common.Point;

public record Forward(int distance) implements Command {

    @Override
    public Point update(Point current) {
        return new Point(current.x() + distance, current.y());
    }

    @Override
    public SubmarinePosition update(SubmarinePosition current) {
        return new SubmarinePosition(current.aim(),
                current.x() + distance,
                current.depth() + current.aim() * distance);
    }

}
