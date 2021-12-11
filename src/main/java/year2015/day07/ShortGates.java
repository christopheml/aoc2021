package year2015.day07;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ShortGates {

    public interface UnaryGate extends Function<Integer, Integer> {}
    public interface BinaryGate extends BiFunction<Integer, Integer, Integer> {}

    public static int AND(int a, int b) {
        return to16bits(a & b);
    }

    public static int OR(int a, int b) {
        return to16bits(a | b);
    }

    public static int LSHIFT(int a, int b) {
        return to16bits(a << b);
    }

    public static int RSHIFT(int a, int b) {
        return to16bits(a >> b);
    }

    public static int NOT(int n) {
        return to16bits(~n);
    }

    private static int to16bits(int n) {
        return n & 0xFFFF;
    }

    public static UnaryGate toUnaryGate(String name) {
        return switch (name) {
            case "NOT" -> ShortGates::NOT;
            case default -> throw new IllegalArgumentException("Unknown unary operator : " + name);
        };
    }

    public static BinaryGate toBinaryGate(String name) {
        return switch (name) {
            case "AND" -> ShortGates::AND;
            case "OR" -> ShortGates::OR;
            case "LSHIFT" -> ShortGates::LSHIFT;
            case "RSHIFT" -> ShortGates::RSHIFT;
            case default -> throw new IllegalArgumentException("Unknown binary operator : " + name);
        };
    }

}
