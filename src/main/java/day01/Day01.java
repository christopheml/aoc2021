package day01;

import common.Input;
import common.ListOps;
import common.Solution;

import java.util.List;

public class Day01 {

    private static long countIncreases(List<Integer> values) {
        return ListOps.grouping(values, 2)
                .stream()
                .filter(l -> l.get(1) > l.get(0))
                .count();
    }

    public static Long partOne(Input input) {
        return countIncreases(input.asList(Integer::valueOf));
    }

    public static Long partTwo(Input input) {
        var windows = ListOps.grouping(input.asList(Integer::valueOf), 3)
                .stream()
                .map(ListOps::sum)
                .toList();
        return countIncreases(windows);
    }

    public static void main(String[] args) {
        new Solution<>(1, Day01::partOne, Day01::partTwo).run();
    }

}
