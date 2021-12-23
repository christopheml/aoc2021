package common.algorithms;

import common.grid.Point;
import common.grid2.ImmutableGrid;
import common.grid2.MutableGrid;
import io.vavr.collection.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class GridDijkstra {

    private final BiFunction<Point, Point, Integer> costFunction;

    public GridDijkstra(BiFunction<Point, Point, Integer> costFunction) {
        this.costFunction = costFunction;
    }

    public List<Point> shortestPath(ImmutableGrid<Integer> grid, Point start, Point end) {
        var d = MutableGrid.compute(grid.width(), grid.height(),
                p -> start.equals(p) ? 0 : Integer.MAX_VALUE, Integer.class);
        var nodes = grid.positions().toList();
        var predecessor = new HashMap<Point, Point>();
        while (!nodes.isEmpty()) {
            var next = nodes.minBy(d::get).getOrElseThrow(IllegalStateException::new);
            nodes = nodes.remove(next);
            grid.directNeighborPositions(next).forEach(neighbor -> {
                var distanceThroughNext = d.get(next) + costFunction.apply(next, neighbor);
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

}
