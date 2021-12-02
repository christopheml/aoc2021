package day02;

import common.Point;

import static java.lang.Integer.parseInt;

public sealed interface Command permits Down, Forward, Up {

    Point update(Point current);
    SubmarinePosition update(SubmarinePosition current);

    static Command fromString(String text) {
        var parts = text.split(" ");
        var distance = parseInt(parts[1]);
        return switch (parts[0]) {
            case "forward" -> new Forward(distance);
            case "down" -> new Down(distance);
            case "up" -> new Up(distance);
            default -> throw new IllegalStateException("Unexpected value: " + parts[0]);
        };
    }

}

