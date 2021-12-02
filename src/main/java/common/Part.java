package common;

import java.util.function.Function;

@FunctionalInterface
public interface Part<T> extends Function<Input, T> {
}
