package common;

import java.util.List;

public final class MathOps {

    private MathOps() {}

    public static <T> T median(List<T> elements) {
        var sorted = elements.stream().sorted().toList();
        var size = sorted.size();
        return sorted.get((size + size % 2) / 2);
    }

    public static <T extends Number> double average(List<T> elements) {
        return elements.stream().mapToDouble(Number::doubleValue).average().orElseThrow();
    }

    public static int bounded(int value, int min, int max) {
        return (value - min) % max + min;
    }

}
