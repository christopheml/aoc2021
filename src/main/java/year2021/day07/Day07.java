package year2021.day07;

import common.StringOps;
import common.runners.Input;
import common.runners.Solution;

import java.util.function.Function;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class Day07 extends Solution<Integer> {

    public Day07() {
        super(2021, 7);
    }

    private int highFuelCost(int distance) {
        return distance * (distance + 1) / 2;
    }

    private int findLowestFuelCost(Input input, Function<Integer, Integer> distanceToFuelCost) {
        var positions = StringOps.asStream(input.asOneLine(), ",", Integer::parseInt).sorted().toList();
        return IntStream.rangeClosed(positions.get(0), positions.get(positions.size() - 1))
                .map(target -> positions.stream().mapToInt(p -> distanceToFuelCost.apply(abs(p - target))).sum())
                .min()
                .getAsInt();
    }

    @Override
    public Integer partOne(Input input) {
        return findLowestFuelCost(input, Function.identity());
    }

    @Override
    public Integer partTwo(Input input) {
        return findLowestFuelCost(input, this::highFuelCost);
    }

}
