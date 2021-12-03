package common;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class ArrayOps {

    public static String asString(int[] array) {
        return Arrays.stream(array).mapToObj(Integer::toString).collect(Collectors.joining(", "));
    }

    public static String asString(long[] array) {
        return Arrays.stream(array).mapToObj(Long::toString).collect(Collectors.joining(", "));
    }

    public static void apply(int[] array1, int[] array2, BiFunction<Integer, Integer, Integer> operation) {
        if (array1.length != array2.length) throw new IllegalArgumentException("Arrays must be the same size");
        for (int i = 0; i < array1.length; ++i) {
            array1[i] = operation.apply(array1[i], array2[i]);
        }
    }

}
