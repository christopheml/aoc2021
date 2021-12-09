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
        return to16bits(b >> a);
    }

    public static int NOT(int n) {
        return to16bits(~n);
    }

    private static int to16bits(int n) {
        return n & 0xFFFF;
    }

}
