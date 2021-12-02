package day02;

import common.Point;

public record Up(int depth) implements Command {
    @Override
    public Point toPoint() {
        return new Point(0, -depth);
    }

    @Override
    public SubmarinePosition toSubmarinePosition() {
        return new SubmarinePosition(-depth, 0, 0);
    }
}
