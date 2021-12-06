package year2021.day06;

import common.runners.Input;
import common.runners.Solution;

import java.util.Arrays;

public class Day06 extends Solution<Long> {
    public Day06() {
        super(2021, 6);
    }

    private LanternFishPopulation getPopulation(Input input) {
        var fishes = Arrays.stream(input.asOneLine().split(","))
                .map(Integer::parseInt)
                .toList();
        return new LanternFishPopulation(fishes);
    }


    private long simulate(Input input, int days) {
        var population = getPopulation(input);

        for (int i = 1; i <= days; ++i) {
            population.tick();
        }
        return population.count();
    }

    @Override
    public Long partOne(Input input) {
        return simulate(input, 80);
    }

    @Override
    public Long partTwo(Input input) {
        return simulate(input, 256);
    }
}
