package year2021.day03;

import common.BinaryString;
import common.collections.ArrayOps;
import common.input.Input;
import common.runners.Solution;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Day03 extends Solution<Integer> {

    public Day03() {
        super(2021, 3);
    }

    // This sums the amount of 1's in every position
    private int[] collectFrequencies(List<BinaryString> numbers) {
        var frequencies = new int[12];
        for (var number : numbers) {
            ArrayOps.apply(frequencies, number.toArray(), Integer::sum);
        }
        return frequencies;
    }

    public Integer partOne(Input input) {
        var inputs = input.asList().map(BinaryString::new);
        var frequencies = collectFrequencies(inputs);
        var gamma = new BinaryString(frequencies, f -> f >= inputs.size() / 2);
        var epsilon = gamma.flip();

        return gamma.toInteger() * epsilon.toInteger();
    }

    private Map<Character, List<Character>> occurrences(List<BinaryString> numbers, int position) {
        return numbers.map(n -> n.getDigit(position)).groupBy(Function.identity());
    }

    private Character mostCommonDigit(List<BinaryString> numbers, int position) {
        var occurrences = occurrences(numbers, position);
        if (occurrences.get('1').get().size() >= occurrences.get('0').get().size()) return '1';
        else return '0';
    }

    private Character leastCommonDigit(List<BinaryString> numbers, int position) {
        var occurrences = occurrences(numbers, position);
        if (occurrences.get('1').get().size() < occurrences.get('0').get().size()) return '1';
        else return '0';
    }


    public BinaryString findMatch(List<BinaryString> numbers, BiFunction<List<BinaryString>, Integer, Character> findBitCriteria, int position) {
        var bitCriteria = findBitCriteria.apply(numbers, position);
        var filtered = numbers.filter(n -> n.getDigit(position) == bitCriteria).toList();
        if (filtered.size() == 1) return filtered.get(0);
        return findMatch(filtered, findBitCriteria, position + 1);
    }

    public Integer partTwo(Input input) {
        var inputs = input.asList().map(BinaryString::new);

        var oxygen = findMatch(inputs, this::mostCommonDigit, 0);
        var co2 = findMatch(inputs, this::leastCommonDigit, 0);

        return oxygen.toInteger() * co2.toInteger();
    }

}
