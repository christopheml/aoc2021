package year2015.day03;

import common.grid.Point;
import common.input.Input;
import common.runners.Solution;
import io.vavr.Tuple;
import io.vavr.Tuple2;

public class Day03 extends Solution<Integer> {

    public Day03() {
        super(2015, 3);
    }

    private Point next(Point previous, char direction) {
        return switch (direction) {
            case 'v' -> previous.move(0, 1);
            case '<' -> previous.move(-1, 0);
            case '^' -> previous.move(0, -1);
            case '>' -> previous.move(1, 0);
            case default -> throw new IllegalStateException();
        };
    }

    @Override
    public Integer partOne(Input input) {
        return input
                .asCharacters()
                .scanLeft(Point.ORIGIN, this::next)
                .toSet()
                .size();
    }

    @Override
    public Integer partTwo(Input input) {
        return input
                .asCharacters()
                .grouped(2)
                .scanLeft(
                        Tuple.of(Point.ORIGIN, Point.ORIGIN), (previous, next) -> Tuple.of(
                                next(previous._1, next.get(0)),
                                next(previous._2, next.get(1))
                        ))
                .flatMap(Tuple2::toSeq)
                .toSet()
                .size();
    }

}
