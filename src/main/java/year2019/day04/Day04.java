package year2019.day04;

import common.runners.Input;
import common.runners.Solution;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class Day04 extends Solution<Long> {

    public Day04() {
        super(2019, 4);
    }

    private IntStream toRange(Input input) {
        var parts = input.asOneLine().split("-");
        return IntStream.rangeClosed(parseInt(parts[0]), parseInt(parts[1]));
    }

    private boolean hasRepeatingDigits(String number) {
        for (int i = 0; i < number.length() - 1; ++i) {
            if (number.charAt(i) == number.charAt(i + 1)) return true;
        }
        return false;
    }

    private boolean hasIncreasingDigits(String number) {
        for (int i = 0; i < number.length() - 1; i++) {
            if (number.charAt(i + 1) < number.charAt(i)) return false;
        }
        return true;
    }

    private boolean hasDoubleDigits(String number) {
        int[] counts = new int[10];
        for (int i = 0; i < number.length(); i++) {
            counts[number.charAt(i) - '0']++;
        }
        return Arrays.stream(counts).filter(i -> i == 2).count() > 0;
    }

    @Override
    public Long partOne(Input input) {
        return toRange(input).mapToObj(Integer::toString)
                .filter(this::hasIncreasingDigits)
                .filter(this::hasRepeatingDigits)
                .count();
    }

    @Override
    public Long partTwo(Input input) {
        return toRange(input).mapToObj(Integer::toString)
                .filter(this::hasIncreasingDigits)
                .filter(this::hasDoubleDigits)
                .count();
    }

}
