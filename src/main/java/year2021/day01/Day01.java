package year2021.day01;

import common.runners.Input;
import common.collections.ListOps;
import common.runners.Solution;

import java.util.List;

public class Day01 extends Solution<Long> {

    public Day01() {
        super(2021, 1);
    }


    private static long countIncreases(List<Integer> values) {
        return ListOps.grouping(values, 2)
                .stream()
                .filter(l -> l.get(1) > l.get(0))
                .count();
    }

    public Long partOne(Input input) {
        return countIncreases(input.asList(Integer::valueOf));
    }

    public Long partTwo(Input input) {
        var windows = ListOps.grouping(input.asList(Integer::valueOf), 3)
                .stream()
                .map(ListOps::sum)
                .toList();
        return countIncreases(windows);
    }

}
