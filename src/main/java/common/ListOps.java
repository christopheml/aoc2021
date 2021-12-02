package common;

import java.util.ArrayList;
import java.util.List;

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

}
