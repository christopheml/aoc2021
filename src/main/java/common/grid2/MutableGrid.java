package common.grid2;

import common.grid.Point;
import io.vavr.collection.List;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface MutableGrid<T> extends ImmutableGrid<T> {

    void updateAll(BiFunction<Point, T, T> operation);
    void updateAll(Function<T, T> operation);

    void set(Point position, T value);
    void update(Point position, Function<T, T> operation);

    static <U> MutableGrid<U> of(List<List<U>> values, Class<U> clazz) {
        return new ArrayGrid<U>(values, clazz);
    }

}
