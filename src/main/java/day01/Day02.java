package day01;

import common.Input;
import common.Point;
import common.Solution;
import day02.Command;
import day02.SubmarinePosition;

import java.util.List;

public class Day02 {

    private static List<Command> commands(Input input) {
        return input.asList(Command::fromString);
    }

    public static Integer partOne(Input input) {
        var position = commands(input).stream().map(Command::toPoint).reduce(Point.ORIGIN, Point::add);
        return position.x() * position.y();
    }

    public static Integer partTwo(Input input) {
        var position = commands(input).stream().map(Command::toSubmarinePosition)
                .reduce(SubmarinePosition.ORIGIN, SubmarinePosition::combine);
        return position.x() * position.depth();
    }

    public static void main(String[] args) {
        new Solution(2, Day02::partOne, Day02::partTwo).run();
    }

}
