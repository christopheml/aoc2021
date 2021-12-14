package year2021.day06;

import common.input.Input;
import common.runners.Solution;

public class Day06 extends Solution<Long> {

    public Day06() {
        super(2021, 6);
    }

    private long simulate(Input input, int days) {
        var population = new LanternFishPopulation(input.asSeparatedIntegers());

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
