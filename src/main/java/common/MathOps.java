package common;

import io.vavr.collection.List;

public final class MathOps {

    private MathOps() {}

    public static <T> T median(List<T> elements) {
        var sorted = elements.sorted();
        var size = sorted.size();
        return sorted.get((size + size % 2) / 2);
    }

    public static <T extends Number> double average(List<T> elements) {
        return elements.average().getOrElseThrow(IllegalStateException::new);
    }

    public static int bounded(int value, int min, int max) {
        return (value - min) % max + min;
    }

}
