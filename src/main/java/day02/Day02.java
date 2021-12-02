package day02;

import common.Input;
import common.ListOps;
import common.Point;
import common.Solution;

import java.util.List;

public class Day02 {

    private static List<Command> commands(Input input) {
        return input.asList(Command::fromString);
    }

    public static Integer partOne(Input input) {
        var finalPosition = ListOps.foldLeft(commands(input), Point.ORIGIN, (position, command) -> command.update(position));
        return finalPosition.x() * finalPosition.y();
    }

    public static Integer partTwo(Input input) {
        var finalPosition = ListOps.foldLeft(commands(input), SubmarinePosition.ORIGIN, (position, command) -> command.update(position));
        return finalPosition.x() * finalPosition.depth();
    }

    public static void main(String[] args) {
        new Solution(2, Day02::partOne, Day02::partTwo).run();
    }

}
