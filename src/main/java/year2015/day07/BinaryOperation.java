package year2015.day07;

import io.vavr.collection.Map;
import year2015.day07.ShortGates.BinaryGate;

public final class BinaryOperation extends CachedWireNode {

    private final String name;
    private final BinaryGate gate;
    private final WireNode operand1;
    private final WireNode operand2;

    public BinaryOperation(String name, BinaryGate gate, WireNode operand1, WireNode operand2) {
        this.name = name;
        this.gate = gate;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public int computeValue() {
        return gate.apply(operand1.getValue(), operand2.getValue());
    }

    @Override
    public void bind(Map<String, WireNode> nodes) {
        operand1.bind(nodes);
        operand2.bind(nodes);
    }

    @Override
    public String toString() {
        return "BinaryOperation{" +
                "name='" + name + '\'' +
                ", operand1=" + operand1 +
                ", operand2=" + operand2 +
                '}';
    }
}
