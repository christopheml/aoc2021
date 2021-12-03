package day3;

import common.ArrayOps;
import common.BinaryString;
import common.Input;
import common.Solution;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day03 {

    // This sums the amount of 1's in every position
    private static int[] collectFrequencies(List<BinaryString> numbers) {
        var frequencies = new int[12];
        for (var number : numbers) {
            ArrayOps.apply(frequencies, number.toArray(), Integer::sum);
        }
        return frequencies;
    }

    public static Integer partOne(Input input) {
        var inputs = input.asList(BinaryString::new);
        var frequencies = collectFrequencies(inputs);
        var gamma = new BinaryString(frequencies, f -> f >= inputs.size() / 2);
        var epsilon = gamma.flip();

        return gamma.toInteger() * epsilon.toInteger();
    }

    private static Map<Character, List<Character>> occurrences(List<BinaryString> numbers, int position) {
        return numbers.stream().map(n -> n.getDigit(position)).collect(Collectors.groupingBy(Function.identity()));
    }

    private static Character mostCommonDigit(List<BinaryString> numbers, int position) {
        var occurrences = occurrences(numbers, position);
        if (occurrences.get('1').size() >= occurrences.get('0').size()) return '1'; else return '0';
    }

    private static Character leastCommonDigit(List<BinaryString> numbers, int position) {
        var occurrences = occurrences(numbers, position);
        if (occurrences.get('1').size() < occurrences.get('0').size()) return '1'; else return '0';
    }


    public static BinaryString findMatch(List<BinaryString> numbers, BiFunction<List<BinaryString>, Integer, Character> findBitCriteria, int position) {
        var bitCriteria = findBitCriteria.apply(numbers, position);
        var filtered = numbers.stream().filter(n -> n.getDigit(position) == bitCriteria).toList();
        if (filtered.size() == 1) return filtered.get(0);
        return findMatch(filtered, findBitCriteria, position + 1);
    }

    public static Integer partTwo(Input input) {
        var inputs = input.asList(BinaryString::new);

        var oxygen = findMatch(inputs, Day03::mostCommonDigit, 0);
        var co2 = findMatch(inputs, Day03::leastCommonDigit, 0);

        return oxygen.toInteger() * co2.toInteger();
    }

    public static void main(String[] args) {
        new Solution<>(3, Day03::partOne, Day03::partTwo).run();
    }

}
