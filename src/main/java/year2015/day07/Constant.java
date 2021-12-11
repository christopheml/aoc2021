package year2015.day07;

import io.vavr.collection.Map;

public record Constant(int value) implements WireNode {

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void bind(Map<String, WireNode> nodes) {
        // No-op
    }

    @Override
    public String toString() {
        return "Constant{" +
                "value=" + value +
                '}';
    }
}
