package day02;

import common.Point;

public record Forward(int distance) implements Command {
    @Override
    public Point toPoint() {
        return new Point(distance, 0);
    }

    @Override
    public SubmarinePosition toSubmarinePosition() {
        return new SubmarinePosition(0, distance, 0);
    }
}
