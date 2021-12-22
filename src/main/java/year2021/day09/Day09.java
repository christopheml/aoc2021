package year2021.day09;

import common.CharOps;
import common.StringOps;
import common.grid.Grid;
import common.grid.InfiniteGrid;
import common.grid.Point;
import common.input.Input;
import common.runners.Solution;
import io.vavr.Tuple2;
import io.vavr.collection.List;

import java.util.function.Function;

public class Day09 extends Solution<Integer> {

    public Day09() {
        super(2021, 9);
    }

    private Grid<Integer> toGrid(Input input) {
        return new Grid<>(input.asList()
                .map(StringOps::toChars)
                .map(List::ofAll)
                .map(chars -> chars.map(CharOps::digitToInt)));
    }

    @Override
    public Integer partOne(Input input) {
        var grid = toGrid(input);
        return grid.indexedElements()
                .filter(t -> grid.directNeighbours(t._1).forAll(p -> p > t._2))
                .map(Tuple2::_2)
                .map(level -> level + 1)
                .sum()
                .intValue();
    }

    private void markGroups(Grid<Integer> grid, InfiniteGrid<Integer> groups, int counter, List<Point> candidates, boolean topLevel) {
        for (var candidate : candidates) {
            var unmarkedNeighbours = grid.directNeighboursPositions(candidate)
                    .filter(p -> groups.get(p) == 0)
                    .filter(p -> grid.get(p) != 9);
            var groupId = topLevel ? ++counter : counter;
            groups.put(candidate, groupId);
            markGroups(grid, groups, counter, unmarkedNeighbours, false);
        }
    }

    @Override
    public Integer partTwo(Input input) {
        var grid = toGrid(input);
        var minimums = grid.indexedElements()
                .filter(t -> grid.directNeighbours(t._1).forAll(p -> p > t._2))
                .map(Tuple2::_1)
                .toList();
        var groups = new InfiniteGrid<>(() -> 0);
        markGroups(grid, groups, 0, minimums, true);
        var groupSizes = List.ofAll(groups.values())
                .filter(i -> i != 0)
                .groupBy(Function.identity())
                .values()
                .map(List::size)
                .sorted();
        return groupSizes.get(groupSizes.size() - 1)
                * groupSizes.get(groupSizes.size() - 2)
                * groupSizes.get(groupSizes.size() - 3);
    }

}
