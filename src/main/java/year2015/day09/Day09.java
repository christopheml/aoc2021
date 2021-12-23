package year2015.day09;

import common.PatternParsers;
import common.WeightedGraph;
import common.input.Input;
import common.runners.Solution;

import java.util.regex.Pattern;

import static java.util.function.Function.identity;

public class Day09 extends Solution<Integer> {

    private static final Pattern inputFormat = Pattern.compile("(\\w+) to (\\w+) = (\\d+)");

    public Day09() {
        super(2015, 9);
    }

    @Override
    public Integer partOne(Input input) {
        var graph = WeightedGraph.nonOriented(input.asStream()
                .map(line -> PatternParsers.parse3(inputFormat, line, identity(), identity(), Integer::parseInt)));
        return graph.traversals()
                .map(graph::cost)
                .min()
                .getOrElseThrow(IllegalStateException::new);
    }

    @Override
    public Integer partTwo(Input input) {
        var graph = WeightedGraph.nonOriented(input.asStream()
                .map(line -> PatternParsers.parse3(inputFormat, line, identity(), identity(), Integer::parseInt)));
        return graph.traversals()
                .map(graph::cost)
                .max()
                .getOrElseThrow(IllegalStateException::new);
    }

}
