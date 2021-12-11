package common.grid2;

import common.grid.Point;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.util.function.*;

public interface ImmutableGrid<T> {

    /**
     * Performs an action for each element of this grid, given its position.
     */
    default void forEach(BiConsumer<Point, T> consumer) {
        positions().forEach(point -> consumer.accept(point, get(point)));
    }

    /**
     * Performs an action for each element of this grid.
     */
    default void forEach(Consumer<T> consumer) {
        elements().forEach(consumer);
    }

    /**
     * Applies a predicate to each element of this grid.
     * @return {@code true} if the predicate is verified for all elements, {@code false} otherwise.
     */
    default boolean forAll(Predicate<T> predicate) {
        return elements().foldLeft(true, (result, value) -> result && predicate.test(value));
    }

    /**
     * Applies a predicate to each element of this grid, given its position.
     * @return {@code true} if the predicate is verified for all elements, {@code false} otherwise.
     */
    default boolean forAll(BiPredicate<Point, T> predicate) {
        return positions().foldLeft(true, (result, position) -> result && predicate.test(position, get(position)));
    }

    default <U> Stream<U> mapElements(BiFunction<Point, T, U> mapper) {
        return positions().map(position -> mapper.apply(position, get(position)));
    }

    default <U> Stream<U> mapElements(Function<T, U> mapper) {
        return elements().map(mapper);
    }

    default int count(Predicate<T> predicate) {
        return elements().count(predicate);
    }

    Stream<T> elements();

    Stream<Point> positions();

    Stream<Point> directNeighborPositions(Point position);

    Stream<Point> allNeighborPositions(Point position);

    /**
     * Returns the value stored in the grid at the given position.
     */
    T get(Point position);

    default <U> U mapElement(Point position, Function<T, U> mapper) {
        return mapper.apply(get(position));
    }

    static <U> ImmutableGrid<U> of(List<List<U>> values) {
        return new ListGrid<>(values);
    }

}
