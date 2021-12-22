package year2021.day01;

import common.input.Input;
import common.runners.Solution;
import io.vavr.collection.Seq;

public class Day01 extends Solution<Long> {

    public Day01() {
        super(2021, 1);
    }

    private static long countIncreases(Seq<Integer> values) {
        return values.sliding(2)
                .count(l -> l.get(1) > l.get(0));
    }

    public Long partOne(Input input) {
        return countIncreases(input.asList().map(Integer::valueOf));
    }

    public Long partTwo(Input input) {
        var windows = input.asList()
                .map(Integer::valueOf)
                .sliding(3)
                .map(l -> l.sum().intValue())
                .toList();
        return countIncreases(windows);
    }

}
