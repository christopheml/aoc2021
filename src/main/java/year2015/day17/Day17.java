package year2015.day17;

import common.runners.Input;
import common.runners.Solution;
import io.vavr.collection.List;

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
        return 0;
    }
}
