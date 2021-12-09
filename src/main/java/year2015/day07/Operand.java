package year2015.day07;

public abstract sealed class Operand {

    public static final class Variable extends Operand {
        private final String name;

        public Variable(String name) {
            this.name = name;
        }

        public String name() {
            return name;
        }
    }

    public static final class Value extends Operand {
        private final int value;

        public Value(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

}
