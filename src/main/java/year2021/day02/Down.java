package year2021.day02;

import common.grid.Point;

public record Down(int depth) implements Command {

    @Override
    public Point update(Point current) {
        return new Point(current.x(), current.y() + depth);
    }

    @Override
    public SubmarinePosition update(SubmarinePosition current) {
        return new SubmarinePosition(current.aim() + depth,
                current.x(),
                current.depth());
    }

}
