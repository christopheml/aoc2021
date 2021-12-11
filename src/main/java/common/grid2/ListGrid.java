package common.grid2;

import common.grid.Point;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.util.function.*;

/**
 * Immutable grid backed by a list of lists.
 * @param <T> Type of the elements of the grid.
 */
public class ListGrid<T> extends BoundedGrid implements ImmutableGrid<T> {

    private final List<List<T>> elements;

    public ListGrid(List<List<T>> values) {
        super(values.head().size(), values.size());
        elements = values;
    }

    @Override
    public Stream<T> elements() {
        return elements.toStream().flatMap(Function.identity());
    }

    @Override
    public T get(Point position) {
        boundsCheck(position);
        return elements.get(position.y()).get(position.x());
    }

}
