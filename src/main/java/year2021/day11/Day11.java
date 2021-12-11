package year2021.day11;

import common.CharOps;
import common.StringOps;
import common.grid.Point;
import common.grid2.MutableGrid;
import common.runners.Input;
import common.runners.Solution;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

public class Day11 extends Solution<Integer> {

    public Day11() {
        super(2021, 11);
    }

    public MutableGrid<Integer> toGrid(Input input) {
        var values = List.ofAll(input.asList())
                .map(StringOps::toChars)
                .map(List::ofAll)
                .map(chars -> chars.map(CharOps::digitToInt));
        return MutableGrid.of(values, Integer.class);
    }

    private void flash(MutableGrid<Integer> grid, Point position) {
        grid.set(position, Integer.MIN_VALUE);
        grid.allNeighborPositions(position)
                .filter(p -> grid.get(p) >= 0)
                .forEach(p -> {
                    grid.update(p, o -> o + 1);
                    if (grid.get(p) > 9) flash(grid, p);
                });
    }

    private int update(MutableGrid<Integer> grid) {
        grid.positions().forEach(position -> {
            grid.update(position, o -> o + 1);
            if (grid.get(position) > 9) flash(grid, position);
        });

        grid.updateAll(o -> Math.max(0, o));
        return grid.count(o -> o == 0);
    }

    @Override
    public Integer partOne(Input input) {
        var grid = toGrid(input);
        return Stream.rangeClosed(1, 100).foldLeft(0, (total, step) -> total + update(grid));
    }

    @Override
    public Integer partTwo(Input input) {
        var grid = toGrid(input);

        for (int i = 1; i <= 1000000; i++) {
            update(grid);
            if (grid.forAll(o -> o == 0)) return i;
        }
        return null;
    }


}
