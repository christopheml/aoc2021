package year2019.day01;

import common.input.Input;
import common.runners.Solution;

public class Day01 extends Solution<Integer> {

    public Day01() {
        super(2019, 1);
    }

    private int directFuelWeight(int moduleWeight) {
        return moduleWeight / 3 - 2;
    }

    private int completeFuelWeight(int moduleWeight) {
        var fuel = directFuelWeight(moduleWeight);
        var increment = directFuelWeight(fuel);
        do {
            fuel += increment;
            increment = directFuelWeight(increment);
        } while (increment > 0);
        return fuel;
    }

    @Override
    public Integer partOne(Input input) {
        var modules = input.asList(Integer::parseInt);
        return modules.stream().mapToInt(this::directFuelWeight).sum();
    }

    @Override
    public Integer partTwo(Input input) {
        var modules = input.asList(Integer::parseInt);
        return modules.stream().mapToInt(this::completeFuelWeight).sum();
    }

}
