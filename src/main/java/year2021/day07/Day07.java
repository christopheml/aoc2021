package year2021.day07;

import common.MathOps;
import common.StringOps;
import common.runners.Input;
import common.runners.Solution;

import java.util.List;
import java.util.function.Function;

import static java.lang.Math.abs;

public class Day07 extends Solution<Integer> {

    public Day07() {
        super(2021, 7);
    }

    private int highFuelCost(int distance) {
        return distance * (distance + 1) / 2;
    }

    private int findLowestFuelCost(Input input, Function<List<Integer>, List<Integer>> targetCandidates, Function<Integer, Integer> distanceToFuelCost) {
        var positions = StringOps.asStream(input.asOneLine(), ",", Integer::parseInt).sorted().toList();
        return targetCandidates.apply(positions).stream().mapToInt(Integer::intValue)
                .map(target -> positions.stream().mapToInt(p -> distanceToFuelCost.apply(abs(p - target))).sum())
                .min()
                .orElseThrow();
    }

    @Override
    public Integer partOne(Input input) {
        return findLowestFuelCost(input, positions -> List.of(MathOps.median(positions)), Function.identity());
    }

    @Override
    public Integer partTwo(Input input) {
        return findLowestFuelCost(input, positions -> {
            var average = MathOps.average(positions);
            // Trying only one of those may fail depending on input data
            return List.of((int) Math.floor(average), (int) Math.ceil(average));
        }, this::highFuelCost);
    }

}
