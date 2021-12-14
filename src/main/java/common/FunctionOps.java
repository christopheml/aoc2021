package common;

import java.util.function.Function;

public final class FunctionOps {

    private FunctionOps() {}

    public static <T> T applyN(T value, Function<T, T> operation, int times) {
        var result = value;
        for (int i = 0; i < times; i++) {
            result = operation.apply(result);
        }
        return result;
    }

}
