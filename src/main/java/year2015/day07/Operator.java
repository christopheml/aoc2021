package year2015.day07;

import year2015.day07.Operand.Value;
import year2015.day07.Operand.Variable;
import year2015.day07.ShortGates.BinaryGate;
import year2015.day07.ShortGates.UnaryGate;

public abstract sealed class Operator {

    public static final class AssignOperator extends Operator {
        private final Variable variable;
        private final Value value;

        public AssignOperator(Variable variable, Value value) {
            this.variable = variable;
            this.value = value;
        }

        public Variable variable() {
            return variable;
        }

        public Value value() {
            return value;
        }
    }

    public static final class BinaryOperator extends Operator {
        private final Operand a;
        private final Operand b;
        private final BinaryGate gate;

        public BinaryOperator(Operand a, Operand b, BinaryGate gate) {
            this.a = a;
            this.b = b;
            this.gate = gate;
        }

        public Operand a() {
            return a;
        }

        public Operand b() {
            return b;
        }

        public BinaryGate gate() {
            return gate;
        }
    }

    public static final class UnaryOperator extends Operator {
        private final Operand a;
        private final UnaryGate gate;

        public UnaryOperator(Operand a, UnaryGate gate) {
            this.a = a;
            this.gate = gate;
        }

        public Operand a() {
            return a;
        }

        public UnaryGate gate() {
            return gate;
        }
    }

    public static Operator parse(String text) {
        // FIXME Implement this
        return null;
    }

}
