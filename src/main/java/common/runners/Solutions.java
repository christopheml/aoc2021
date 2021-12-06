package common.runners;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Solutions {

    public static final List<Class<? extends Solution<?>>> ALL = getSolutions();

    private static List<Class<? extends Solution<?>>> getSolutions() {
        var solutions = new ArrayList<Class< ? extends Solution<?>>>();
        for (int day = 0; day <= 31; ++day) {
            var solution = getSolution(day);
            Optional.ofNullable(solution).ifPresent(solutions::add);
        }
        return solutions;
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends Solution<?>> getSolution(int day) {
        try {
            return (Class<? extends Solution<?>>) Solutions.class.getClassLoader()
                    .loadClass(String.format("day%1$02d.Day%1$02d", day));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}
