package year2015.day07;

import io.vavr.collection.Map;
import year2015.day07.ShortGates.UnaryGate;

public final class UnaryOperation extends CachedWireNode {

    private final String name;
    private final UnaryGate gate;
    private final WireNode operand;

    public UnaryOperation(String name, UnaryGate gate, WireNode operand) {
        this.name = name;
        this.gate = gate;
        this.operand = operand;
    }

    @Override
    public int computeValue() {
        return gate.apply(operand.getValue());
    }

    @Override
    public void bind(Map<String, WireNode> nodes) {
        operand.bind(nodes);
    }

    @Override
    public String toString() {
        return "UnaryOperation{" +
                "name='" + name + '\'' +
                ", operand=" + operand +
                '}';
    }
}
