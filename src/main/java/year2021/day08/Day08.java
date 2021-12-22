package year2021.day08;

import common.StringOps;
import common.input.Input;
import common.runners.Solution;
import io.vavr.Tuple2;
import io.vavr.collection.List;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day08 extends Solution<Integer> {

    private static final List<List<Character>> segments = List.of(
            List.of('a', 'b', 'c', 'e', 'f', 'g'),
            List.of('c', 'f'),
            List.of('a', 'c', 'd', 'e', 'g'),
            List.of('a', 'c', 'd', 'f', 'g'),
            List.of('b', 'c', 'd', 'f'),
            List.of('a', 'b', 'd', 'f', 'g'),
            List.of('a', 'b', 'd', 'e', 'f', 'g'),
            List.of('a', 'c', 'f'),
            List.of('a', 'b', 'c', 'd', 'e', 'f', 'g'),
            List.of('a', 'b', 'c', 'd', 'f', 'g')
    );


    public Day08() {
        super(2021, 8);
    }

    @Override
    public Integer partOne(Input input) {
        return input.asStreamOfLines()
                .map(Entry::fromString)
                .flatMap(Entry::digits)
                .count(Digit::isTrivial);
    }

    @Override
    public Integer partTwo(Input input) {
        return input.asStreamOfLines()
                .map(Entry::fromString)
                .map(this::solve)
                .reduce(Integer::sum);
    }

    private int solve(Entry entry) {
        var mappings = new Mappings();

        var one = getSignalsFromUniqueDigit(entry.patterns(), 2);
        mappings.narrow(segments.get(1), one);

        var four = getSignalsFromUniqueDigit(entry.patterns(), 4);
        mappings.narrow(segments.get(4), four);

        var seven = getSignalsFromUniqueDigit(entry.patterns(), 3);
        mappings.narrow(segments.get(7), seven);

        var eight = getSignalsFromUniqueDigit(entry.patterns(), 7);

        mappings.narrow('a', seven.reject(one::contains));
        mappings.narrow(List.of('e', 'g'), eight.reject(one::contains).reject(seven::contains).reject(four::contains));
        mappings.narrow(List.of('b', 'd'), four.reject(one::contains));

        // Three signals have a unique count between digits
        var counts = characterCount(entry.patterns());
        mappings.narrow('e', findSignalWithCount(counts, 4));
        mappings.narrow('f', findSignalWithCount(counts, 9));
        mappings.narrow('b', findSignalWithCount(counts, 6));

        mappings.reduce();

        // Should be solved by now
        return new Translator(mappings).translate(entry.digits());
    }

    private Map<Character, Integer> characterCount(List<String> patterns) {
        return segments.get(8)
                .map(c -> new Tuple2<>(c, patterns.count(p -> p.contains(c.toString()))))
                .toMap(Function.identity())
                .toJavaMap();
    }

    private List<Character> getSignalsFromUniqueDigit(List<String> patterns, int signalCount) {
        return List.ofAll(StringOps.toChars(patterns.filter(p -> p.length() == signalCount).head()));
    }

    private List<Character> findSignalWithCount(Map<Character, Integer> counts, int count) {
        return List.of(counts.entrySet().stream().filter(kv -> kv.getValue() == count).findFirst().orElseThrow().getKey());
    }

    private static class Mappings {
        private final Map<Character, Set<Character>> mappings = new HashMap<>();

        public Mappings() {
            var all = Set.of('a', 'b', 'c', 'd', 'e', 'f', 'g');
            all.forEach(c -> mappings.put(c, new HashSet<>(all)));
        }

        public void narrow(List<Character> signals, List<Character> possibilities) {
            signals.forEach(s -> narrow(s, possibilities));
        }

        public void narrow(Character signal, List<Character> possibilities) {
            mappings.get(signal).retainAll(possibilities.asJava());
        }

        public void reduce() {
            var solvedValues = mappings.entrySet().stream().filter(kv -> kv.getValue().size() == 1).toList();
            mappings.forEach((key, value) -> solvedValues.forEach(solved -> {
                if (solved.getKey() != key) {
                    value.removeAll(solved.getValue());
                }
            }));
        }

        public Map<Character, Character> solvedMap() {
            return mappings.entrySet().stream()
                    .collect(Collectors.toMap(kv -> kv.getValue().stream().findFirst().orElseThrow(), Map.Entry::getKey));
        }
    }

    private static class Translator {
        private final Map<Character, Character> dictionary;

        public Translator(Mappings mappings) {
            dictionary = mappings.solvedMap();
        }

        private int translateDigit(String digit) {
            return segments.indexOf(List.ofAll(StringOps.toChars(digit))
                    .map(dictionary::get)
                    .sorted());
        }

        public int translate(List<String> digits) {
            var result = 0;
            for (var digit : digits) {
                result *= 10;
                result += translateDigit(digit);
            }
            return result;
        }
    }

}
