package year2015.day20;

import common.input.Input;
import common.runners.Solution;

public class Day20 extends Solution<Integer> {

    public Day20() {
        super(2015, 20);
    }

    @Override
    public Integer partOne(Input input) {
        var target = Integer.parseInt(input.asOneLine());
        final var houses = new int[target / 10];

        for (int elf = 1; elf <= target / 10; elf++) {
            for (int position = elf; position < houses.length; position += elf) {
                houses[position] += elf * 10;
            }
        }

        return findFirstHouse(target, houses);
    }

    @Override
    public Integer partTwo(Input input) {
        var target = Integer.parseInt(input.asOneLine());
        final var houses = new int[target / 10];

        for (int elf = 1; elf <= target / 10; elf++) {
            for (int i = 1; i <= 50; ++i) {
                var position = i * elf;
                if (position < houses.length) {
                    houses[position] += elf * 11;
                } else break;
            }
        }

        return findFirstHouse(target, houses);
    }

    private int findFirstHouse(int target, int[] houses) {
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] >= target) {
                return i;
            }
        }
        throw new IllegalStateException();
    }

}
