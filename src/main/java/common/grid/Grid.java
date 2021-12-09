package common.grid;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.util.Optional;
import java.util.function.Function;

public class Grid<T> {

    private final List<List<T>> elements;
    private final int width;
    private final int height;

    public Grid(List<List<T>> elements) {
        this.elements = elements;
        height = elements.length();
        width = elements.get(0).length();
    }

    private List<Point> allNeighboursPositions(int x, int y) {
        return List.of(
                new Point(x - 1, y - 1), new Point(x - 1, y), new Point(x - 1, y + 1),
                new Point(x, y - 1), new Point(x, y + 1),
                new Point(x + 1, y - 1), new Point(x + 1, y), new Point(x + 1, y + 1)
        ).filter(p -> p.applyFunction(this::inBounds));
    }

    private List<Point> directNeighboursPositions(int x, int y) {
        return List.of(
                new Point(x - 1, y),
                new Point(x, y - 1), new Point(x, y + 1),
                new Point(x + 1, y)
        ).filter(p -> p.applyFunction(this::inBounds));
    }

    public List<Point> allNeighboursPositions(Point p) {
        return p.applyFunction(this::allNeighboursPositions);
    }

    public List<Point> directNeighboursPositions(Point p) {
        return p.applyFunction(this::directNeighboursPositions);
    }

    public Stream<Point> positions() {
        return Stream.range(0, height).flatMap(y -> Stream.range(0, width).map(x -> new Point(x, y)));
    }

    public Stream<Tuple2<Point, T>> indexedElements() {
        return positions().map(p -> new Tuple2<>(p, get(p)));
    }

    public List<T> allNeighbours(Point p) {
        return p.applyFunction(this::allNeighbours);
    }

    public List<T> directNeighbours(Point p) {
        return p.applyFunction(this::directNeighbours);
    }

    public T get(int x, int y) {
        return safeGet(x, y).orElseThrow();
    }

    public T get(Point p) {
        return get(p.x(), p.y());
    }

    public List<T> elements() {
        return elements.flatMap(Function.identity());
    }

    private Optional<T> safeGet(int x, int y) {
        return inBounds(x, y) ? Optional.of(elements.get(y).get(x)) : Optional.empty();
    }

    private Optional<T> safeGet(Point p) {
        return safeGet(p.x(), p.y());
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    private List<T> allNeighbours(int x, int y) {
        return allNeighboursPositions(x, y)
                .map(this::safeGet)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private List<T> directNeighbours(int x, int y) {
        return directNeighboursPositions(x, y)
                .map(this::safeGet)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

}
