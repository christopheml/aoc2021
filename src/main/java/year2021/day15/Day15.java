package year2021.day15;

import common.CharOps;
import common.MathOps;
import common.StringOps;
import common.algorithms.Dijkstra;
import common.grid.Point;
import common.grid2.ImmutableGrid;
import common.input.Input;
import common.runners.Solution;
import io.vavr.collection.List;

public class Day15 extends Solution<Integer> {

    public Day15() {
        super(2021, 15);
    }

    // FIXME Import this in Input
    private ImmutableGrid<Integer> toGrid(Input input) {
        return ImmutableGrid.of(input.asList()
                .map(StringOps::toChars)
                .map(List::ofAll)
                .map(chars -> chars.map(CharOps::digitToInt)));
    }

    @Override
    public Integer partOne(Input input) {
        var cave = toGrid(input);
        var dijkstra = new Dijkstra((point, neighbor) -> cave.get(neighbor));
        var path = dijkstra.shortestPath(cave, Point.ORIGIN, cave.maxPosition());
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
        var dijkstra = new Dijkstra((point, neighbor) -> hugeCave.get(neighbor));
        var path = dijkstra.shortestPath(hugeCave, Point.ORIGIN, hugeCave.maxPosition());
        return path.remove(Point.ORIGIN).map(hugeCave::get).sum().intValue();
    }

}
