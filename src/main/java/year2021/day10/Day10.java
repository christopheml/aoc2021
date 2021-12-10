package year2021.day10;

import common.StringOps;
import common.runners.Input;
import common.runners.Solution;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.util.Stack;
import java.util.function.Function;

public class Day10 extends Solution<Long> {

    public Day10() {
        super(2021, 10);
    }

    private static final Map<Character, Character> matching = HashMap.of(
            ')', '(', ']', '[', '>', '<', '}', '{'
    );

    private static final Map<Character, Character> reverseMatching = matching.map(
            (c1, c2) -> new Tuple2<>(c2, c1)
    );

    private static final Map<Character, Integer> invalidCharacterPoints = HashMap.of(
            ')', 3, ']', 57, '}', 1197, '>', 25137
    );

    private static final Map<Character, Integer> missingCharacterPoints = HashMap.of(
            ')', 1, ']', 2, '}', 3, '>', 4
    );

    private static sealed class ParseResult {
        private static final class CorruptedLine extends ParseResult {
            public final Character invalidCharacter;

            public CorruptedLine(Character invalidCharacter) {
                this.invalidCharacter = invalidCharacter;
            }
        }

        private static final class IncompleteLine extends ParseResult {
            public final Stack<Character> remaining;

            public IncompleteLine(Stack<Character> remaining) {
                this.remaining = remaining;
            }
        }
    }

    private ParseResult parse(String line) {
        var chars = StringOps.toChars(line);
        var stack = new Stack<Character>();
        for (var c : chars) {
            if (matching.values().contains(c)) {
                stack.push(c);
            } else {
                var top = stack.peek();
                if (top != matching.getOrElse(c, '!')) return new ParseResult.CorruptedLine(c);
                stack.pop();
            }
        }
        return new ParseResult.IncompleteLine(stack);
    }

    @Override
    public Long partOne(Input input) {
        return List.ofAll(input.asStreamOfLines())
                .map(this::parse)
                .filter(result -> result instanceof ParseResult.CorruptedLine)
                .map(result -> ((ParseResult.CorruptedLine) result).invalidCharacter)
                .groupBy(Function.identity())
                .map(entry -> invalidCharacterPoints.get(entry._1).get() * entry._2.length())
                .sum()
                .longValue();
    }

    @Override
    public Long partTwo(Input input) {
        var scores = List.ofAll(input.asStreamOfLines())
                .map(this::parse)
                .filter(result -> result instanceof ParseResult.IncompleteLine)
                .map(result -> ((ParseResult.IncompleteLine) result).remaining)
                .map(remaining -> List.ofAll(remaining)
                        .reverse()
                        .map(c -> reverseMatching.get(c).get())
                        .map(c -> missingCharacterPoints.get(c).get())
                        .foldLeft(0L, (sum, score) -> sum * 5 + score))
                .sorted();
        return scores.get(scores.length() / 2);
    }

}
