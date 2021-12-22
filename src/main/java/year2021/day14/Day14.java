package year2021.day14;

import common.FunctionOps;
import common.StringOps;
import common.input.Input;
import common.input.InputGroup;
import common.runners.Solution;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.util.HashMap;

public class Day14 extends Solution<Long> {

    public Day14() {
        super(2021, 14);
    }

    @Override
    public Long partOne(Input input) {
        var groups = input.asGroups();
        var polymer = groups.get(0).asOneLine();
        var substitutions = createSubstitutions(groups.get(1));
        HashMap<String, Long> frequencies = createFrequencies(polymer);

        frequencies = FunctionOps.applyN(frequencies, f -> updateFrequencies(substitutions, f), 10);

        return answer(frequencies);
    }

    @Override
    public Long partTwo(Input input) {
        var groups = input.asGroups();
        var polymer = groups.get(0).asOneLine();
        var substitutions = createSubstitutions(groups.get(1));

        HashMap<String, Long> frequencies = createFrequencies(polymer);

        frequencies = FunctionOps.applyN(frequencies, f -> updateFrequencies(substitutions, f), 40);

        return answer(frequencies);
    }

    private Map<String, Tuple2<String, String>> createSubstitutions(InputGroup lines) {
        return lines.asStreamOfLines()
                .map(StringOps::toTuple)
                .toMap(t -> new Tuple2<>(t._1, new Tuple2<>(t._1.charAt(0) + t._2, t._2 + t._1.charAt(1))));
    }

    private HashMap<String, Long> createFrequencies(String polymer) {
        var frequencies = new HashMap<String, Long>(18);
        for (int i = 0; i < polymer.length() - 1; i++) {
            var pair = polymer.substring(i, i + 2);
            frequencies.computeIfPresent(pair, (p, count) -> count + 1);
            frequencies.putIfAbsent(pair, 1L);
        }
        return frequencies;
    }

    private HashMap<String, Long> updateFrequencies(Map<String, Tuple2<String, String>> substitutions, HashMap<String, Long> frequencies) {
        var updatedFrequencies = new HashMap<String, Long>();
        substitutions.forEach(substitution -> {
            var count = frequencies.computeIfAbsent(substitution._1, v -> 0L);
            updatedFrequencies.computeIfPresent(substitution._2._1, (p, c) -> c + count);
            updatedFrequencies.computeIfPresent(substitution._2._2, (p, c) -> c + count);
            updatedFrequencies.putIfAbsent(substitution._2._1, count);
            updatedFrequencies.putIfAbsent(substitution._2._2, count);
        });
        return updatedFrequencies;
    }

    private long answer(java.util.Map<String, Long> frequencies) {
        var characterFrequencies = io.vavr.collection.HashMap.ofAll(frequencies).flatMap(t ->
                        List.of(
                                new Tuple2<>(t._1.charAt(0), t._2),
                                new Tuple2<>(t._1.charAt(1), t._2)
                        )
                ).groupBy(t -> t._1)
                .mapValues(counts -> counts.map(Tuple2::_2).sum().longValue())
                .values()
                .sorted();

        return (characterFrequencies.last() - characterFrequencies.head() + 1) / 2;
    }

}
