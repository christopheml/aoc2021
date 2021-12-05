package common.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class ListOps {

    public static <T> List<List<T>> grouping(List<T> list, int size) {
        var result = new ArrayList<List<T>>();
        for (int i = 0; i < list.size() - (size - 1); ++i) {
            result.add(list.subList(i, i + size));
        }
        return result;
    }

    public static int sum(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    public static <T, U> T foldLeft(List<U> list, T neutral, BiFunction<T, U, T> combiner) {
        var acc = neutral;
        for (var element : list) {
            acc = combiner.apply(acc, element);
        }
        return acc;
    }

}
