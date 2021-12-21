package year2021.day07;

import common.MathOps;
import common.input.Input;
import common.runners.Solution;
import io.vavr.collection.List;

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
        var positions = input.asSeparatedIntegers().sorted();
        return targetCandidates.apply(positions).map(Integer::intValue)
                .map(target -> positions.map(p -> distanceToFuelCost.apply(abs(p - target))).sum().intValue())
                .min()
                .getOrElseThrow(IllegalStateException::new);
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
