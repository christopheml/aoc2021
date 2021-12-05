package common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class InfiniteGrid<T> {
    private final Supplier<T> emptyValue;
    private final Map<Point, T> values = new HashMap<>();

    public InfiniteGrid(Supplier<T> emptyValue) {
        this.emptyValue = emptyValue;
    }

    public T get(Point coordinates) {
        return values.computeIfAbsent(coordinates, p -> emptyValue.get());
    }

    public T put(Point coordinates, T value) {
        return values.put(coordinates, value);
    }

    public Set<Map.Entry<Point, T>> entrySet() {
        return values.entrySet();
    }

    public Set<Point> keySet() {
        return values.keySet();
    }

    public Collection<T> values() {
        return values.values();
    }

}
