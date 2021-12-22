package common.runners;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Solutions {

    public static final List<Class<? extends Solution<?>>> ALL = getSolutions(2021);

    public static List<Class<? extends Solution<?>>> getSolutions(int year) {
        var solutions = new ArrayList<Class< ? extends Solution<?>>>();
        for (int day = 0; day <= 31; ++day) {
            var solution = getSolution(year, day);
            Optional.ofNullable(solution).ifPresent(solutions::add);
        }
        return solutions;
    }

    @SuppressWarnings("unchecked")
    public static Class<? extends Solution<?>> getSolution(int year, int day) {
        try {
            return (Class<? extends Solution<?>>) Solutions.class.getClassLoader()
                    .loadClass(String.format("year%d.day%2$02d.Day%2$02d", year, day));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}
