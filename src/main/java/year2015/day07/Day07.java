package year2015.day07;

import common.input.Input;
import common.runners.Solution;
import io.vavr.Tuple2;
import io.vavr.collection.Stream;

import java.util.function.Function;

import static year2015.day07.ShortGates.toBinaryGate;
import static year2015.day07.ShortGates.toUnaryGate;

public class Day07 extends Solution<Integer> {

    public Day07() {
        super(2015, 7);
    }

    private WireNode toOperand(String text) {
        try {
            var value = Integer.parseInt(text);
            return new Constant(value);
        } catch (NumberFormatException e) {
            return new UnknownNode(text);
        }
    }

    private Tuple2<String, WireNode> parse(String text) {
        var parts = text.split(" ");
        return switch (parts.length) {
            case 3 -> new Tuple2<>(parts[2], toOperand(parts[0]));
            case 4 -> new Tuple2<>(parts[3], new UnaryOperation(parts[0], toUnaryGate(parts[0]), toOperand(parts[1])));
            case 5 -> new Tuple2<>(parts[4], new BinaryOperation(parts[1], toBinaryGate(parts[1]), toOperand(parts[0]), toOperand(parts[2])));
            case default -> throw new IllegalArgumentException("Unknown operator : " + text);
        };
    }

    @Override
    public Integer partOne(Input input) {
        var nodes = Stream.ofAll(input.asStreamOfLines())
                .map(this::parse)
                .toMap(Function.identity());
        nodes.values().forEach(node -> node.bind(nodes));

        return nodes.get("a").get().getValue();
    }

    @Override
    public Integer partTwo(Input input) {
        var nodes = Stream.ofAll(input.asStreamOfLines())
                .map(this::parse)
                .toMap(Function.identity());

        var patched = nodes.put("b", new Constant(956));
        patched.values().forEach(node -> node.bind(patched));

        return patched.get("a").get().getValue();
    }

}
