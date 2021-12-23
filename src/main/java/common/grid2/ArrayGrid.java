package common.grid2;

import common.grid.Point;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ArrayGrid<T> extends BoundedGrid implements MutableGrid<T> {

    private final T[] elements;

    protected ArrayGrid(List<List<T>> values, Class<T> clazz) {
        super(values.head().size(), values.size());
        elements = (T[]) Array.newInstance(clazz, width * height);
        updateAll((position, val) -> values.get(position.y()).get(position.x()));
    }

    protected ArrayGrid(int width, int height, T defaultValue, Class<T> clazz) {
        super(width, height);
        elements = (T[]) Array.newInstance(clazz, width * height);
        Arrays.fill(elements, defaultValue);
    }

    @Override
    public Stream<T> elements() {
        return Stream.ofAll(Arrays.stream(elements));
    }

    @Override
    public T get(Point position) {
        boundsCheck(position);
        return elements[index(position)];
    }

    @Override
    public void updateAll(BiFunction<Point, T, T> operation) {
        positions().forEach(position -> set(position, operation.apply(position, get(position))));
    }

    @Override
    public void updateAll(Function<T, T> operation) {
        for (int i = 0; i < elements.length; ++i) {
            elements[i] = operation.apply(elements[i]);
        }
    }

    @Override
    public void updateRegion(Point start, Point end, Function<T, T> operation) {
        for (int y = start.y(); y <= end.y(); y++) {
            for (int x = start.x(); x <= end.x(); x++) {
                var i = index(x, y);
                elements[i] = operation.apply(elements[i]);
            }
        }
    }

    @Override
    public void set(Point position, T value) {
        elements[index(position)] = value;
    }

    @Override
    public void update(Point position, Function<T, T> operation) {
        set(position, operation.apply(get(position)));
    }

    private int index(Point position) {
        return index(position.x(), position.y());
    }

    private int index(int x, int y) {
        return y * width + x;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]);
            if (i % width == width - 1) sb.append('\n');
        }
        sb.append('\n');
        return sb.toString();
    }

}
