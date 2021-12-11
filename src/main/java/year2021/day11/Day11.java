package year2021.day11;

import common.CharOps;
import common.StringOps;
import common.grid.IntGrid;
import common.grid.Point;
import common.runners.Input;
import common.runners.Solution;
import io.vavr.collection.List;

public class Day11 extends Solution<Integer> {

    public Day11() {
        super(2021, 11);
    }

    public IntGrid toGrid(Input input) {
        return new IntGrid(List.ofAll(input.asList())
                .map(StringOps::toChars)
                .map(List::ofAll)
                .map(chars -> chars.map(CharOps::digitToInt)));
    }

    private void flash(IntGrid grid, Point position) {
        grid.set(position.x(), position.y(), Integer.MIN_VALUE);
        grid.allNeighbors(position.x(), position.y())
                .filter(p -> grid.get(p.x(), p.y()) >= 0)
                .forEach(p -> {
                    grid.inc(p.x(), p.y());
                    if (grid.get(p.x(), p.y()) > 9) flash(grid, p);
                });
    }

    @Override
    public Integer partOne(Input input) {
        var grid = toGrid(input);

        var flashCount = 0;
        for (int i = 1; i <= 100; i++) {
            for (int y = 0; y < grid.height; ++y) {
                for (int x = 0; x < grid.width; ++x) {
                    grid.inc(x, y);
                    if (grid.get(x, y) > 9) {
                        flash(grid, new Point(x, y));
                    }
                }
            }
            for (int y = 0; y < grid.height; ++y) {
                for (int x = 0; x < grid.width; ++x) {
                    if (grid.get(x, y) < 0) {
                        flashCount++;
                        grid.set(x, y, 0);
                    }
                }
            }
        }

        return flashCount;
    }

    @Override
    public Integer partTwo(Input input) {
        var grid = toGrid(input);

        for (int i = 1; i <= 1000000; i++) {
            for (int y = 0; y < grid.height; ++y) {
                for (int x = 0; x < grid.width; ++x) {
                    grid.inc(x, y);
                    if (grid.get(x, y) > 9) {
                        flash(grid, new Point(x, y));
                    }
                }
            }
            for (int y = 0; y < grid.height; ++y) {
                for (int x = 0; x < grid.width; ++x) {
                    if (grid.get(x, y) < 0) {
                        grid.set(x, y, 0);
                    }
                }
            }
            if (grid.forALl(o -> o == 0)) return i;
        }
        return null;
    }


}
