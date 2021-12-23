package common.grid2;

import common.grid.Point;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface MutableGrid<T> extends ImmutableGrid<T> {

    void updateAll(BiFunction<Point, T, T> operation);

    void updateAll(Function<T, T> operation);

    void set(Point position, T value);

    void update(Point position, Function<T, T> operation);

    static <U> MutableGrid<U> of(List<List<U>> values, Class<U> clazz) {
        return new ArrayGrid<>(values, clazz);
    }

    // FIXME Extra slow, figure out a better way to initialize the grid
    static <U> MutableGrid<U> compute(int width, int height, Function<Point, U> defaultValue, Class<U> clazz) {
        var values = Stream.range(0, height)
                .map(y -> Stream.range(0, width)
                        .map(x -> defaultValue.apply(new Point(x, y)))
                        .toList())
                .toList();
        return new ArrayGrid<>(values, clazz);
    }

    static <U> MutableGrid<U> init(int width, int height, U defaultValue, Class<U> clazz) {
        return new ArrayGrid<>(width, height, defaultValue, clazz);
    }

}
