package year2021.day15;

import common.CharOps;
import common.MathOps;
import common.StringOps;
import common.grid.Point;
import common.grid2.ImmutableGrid;
import common.grid2.MutableGrid;
import common.input.Input;
import common.runners.Solution;
import io.vavr.collection.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day15 extends Solution<Integer> {

    public Day15() {
        super(2021, 15);
    }

    // FIXME Import this in Input
    private ImmutableGrid<Integer> toGrid(Input input) {
        return ImmutableGrid.of(List.ofAll(input.asList())
                .map(StringOps::toChars)
                .map(List::ofAll)
                .map(chars -> chars.map(CharOps::digitToInt)));
    }

    private List<Point> dijkstra(ImmutableGrid<Integer> grid) {
        var d = MutableGrid.compute(grid.width(), grid.height(),
                p -> Point.ORIGIN.equals(p) ? 0 : Integer.MAX_VALUE, Integer.class);
        var nodes = grid.positions().toList();
        var predecessor = new HashMap<Point, Point>();
        while (!nodes.isEmpty()) {
            var next = nodes.minBy(d::get).getOrElseThrow(IllegalStateException::new);
            nodes = nodes.remove(next);
            grid.directNeighborPositions(next).forEach(neighbor -> {
                var distanceThroughNext = d.get(next) + grid.get(neighbor);
                if (d.get(neighbor) > distanceThroughNext) {
                    d.set(neighbor, distanceThroughNext);
                    predecessor.put(neighbor, next);
                }
            });
        }

        return path(predecessor, Point.ORIGIN, grid.maxPosition());
    }

    private List<Point> path(Map<Point, Point> predecessor, Point start, Point end) {
        var current = end;
        var path = new ArrayList<Point>();
        while (!current.equals(start)) {
            path.add(0, current);
            current = predecessor.get(current);
        }
        return List.ofAll(path);
    }

    @Override
    public Integer partOne(Input input) {
        var cave = toGrid(input);
        var path = dijkstra(cave);
        return path.remove(Point.ORIGIN).map(cave::get).sum().intValue();
    }

    @Override
    public Integer partTwo(Input input) {
        var initialCave = toGrid(input);
        var hugeCave = ImmutableGrid.compute(initialCave.width() * 5, initialCave.height() * 5,
                p -> {
                    if (initialCave.contains(p)) return initialCave.get(p);
                    return MathOps.bounded(initialCave.get(new Point(p.x() % initialCave.width(), p.y() % initialCave.height())) +
                            p.x() / initialCave.width() +
                            p.y() / initialCave.height(), 1, 9);
                });
        var path = dijkstra(hugeCave);
        return path.remove(Point.ORIGIN).map(hugeCave::get).sum().intValue();
    }

}
