package common.grid;

import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.util.function.Function;
import java.util.function.Predicate;

public class IntGrid {

    public final int width;
    public final int height;
    private final int[] elements;

    public IntGrid(List<List<Integer>> values) {
        width = values.get(0).size();
        height = values.size();
        elements = new int[height * width];
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                set(x, y, values.get(y).get(x));
            }
        }
    }

    public void inc(int x, int y) {
        set(x, y, get(x, y) + 1);
    }

    public void set(int x, int y, int value) {
        elements[index(x, y)] = value;
    }

    public int get(int x, int y) {
        return elements[index(x, y)];
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    private int index(int x, int y) {
        return y * width + x;
    }

    public void forAll(Function<Integer, Integer> transformation) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = transformation.apply(elements[i]);
        }
    }

    public Stream<Point> allNeighbors(int x, int y) {
        return Stream.of(
                new Point(x - 1, y - 1), new Point(x - 1, y), new Point(x - 1, y + 1),
                new Point(x, y - 1), new Point(x, y + 1),
                new Point(x + 1, y - 1), new Point(x + 1, y), new Point(x + 1, y + 1)
        ).filter(p -> p.applyFunction(this::inBounds));
    }

    public Stream<Point> positions() {
        return Stream.range(0, height).flatMap(y -> Stream.range(0, width).map(x -> new Point(x, y)));
    }

    public boolean forALl(Predicate<Integer> predicate) {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                if (!predicate.test(get(x, y))) return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                sb.append(elements[index(x, y)]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
