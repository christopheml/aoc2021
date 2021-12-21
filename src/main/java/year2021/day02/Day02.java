package year2021.day02;

import common.grid.Point;
import common.input.Input;
import common.runners.Solution;
import io.vavr.collection.List;

public class Day02 extends Solution<Integer> {

    public Day02() {
        super(2021, 2);
    }

    private List<Command> commands(Input input) {
        return input.asList(Command::fromString);
    }

    public Integer partOne(Input input) {
        var finalPosition = commands(input).foldLeft(Point.ORIGIN, (position, command) -> command.update(position));
        return finalPosition.x() * finalPosition.y();
    }

    public Integer partTwo(Input input) {
        var finalPosition = commands(input).foldLeft(SubmarinePosition.ORIGIN, (position, command) -> command.update(position));
        return finalPosition.x() * finalPosition.depth();
    }

}
