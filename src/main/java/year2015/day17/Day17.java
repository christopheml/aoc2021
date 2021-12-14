package year2015.day17;

import common.input.Input;
import common.runners.Solution;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Traversable;

public class Day17 extends Solution<Integer> {

    public Day17() {
        super(2015, 17);
    }

    public int target = 150;

    @Override
    public Integer partOne(Input input) {
        return List.ofAll(input.asList(Integer::parseInt))
                .combinations()
                .count(selection -> selection.sum().intValue() == target);
    }

    @Override
    public Integer partTwo(Input input) {
        var smallestCombinations = List.ofAll(input.asList(Integer::parseInt))
                .combinations()
                .filter(selection -> selection.sum().intValue() == target)
                .groupBy(Traversable::size)
                .minBy(Tuple2::_1)
                .getOrElseThrow(IllegalStateException::new);
        return smallestCombinations._2.size();
    }
}
