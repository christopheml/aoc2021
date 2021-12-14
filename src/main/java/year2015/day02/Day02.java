package year2015.day02;

import common.StringOps;
import common.input.Input;
import common.runners.Solution;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

public class Day02 extends Solution<Integer> {

    public Day02() {
        super(2015, 2);
    }

    @Override
    public Integer partOne(Input input) {
        return presents(input)
                .map(d -> 3 * d.get(0) * d.get(1) + 2 * d.get(1) * d.get(2) + 2 * d.get(0) * d.get(2))
                .sum()
                .intValue();
    }

    @Override
    public Integer partTwo(Input input) {
        return presents(input)
                .map(d -> 2 * (d.get(0) + d.get(1)) + d.get(0) * d.get(1) * d.get(2))
                .sum()
                .intValue();
    }

    private Stream<List<Integer>> presents(Input input) {
        return Stream.ofAll(input.asStreamOfLines())
                .map(line -> List.ofAll(StringOps.asList(line, "x", Integer::parseInt)))
                .map(List::sorted);
    }

}
