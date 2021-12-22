package year2021.day12;

import common.StringOps;
import common.input.Input;
import common.runners.Solution;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.util.function.Function;

public class Day12 extends Solution<Integer> {

    public Day12() {
        super(2021, 12);
    }

    public static class Caves {
        private final Map<String, List<String>> graph;

        public Caves(Map<String, List<String>> graph) {
            this.graph = graph;
        }

        private boolean canVisit(String cave, List<String> visited) {
            return StringOps.isUpperCase(cave) || !visited.contains(cave);
        }

        private boolean canVisit(String cave, List<String> visited, boolean visitedTwice) {
            if (StringOps.isUpperCase(cave)) return true;
            return visited.count(cave::equals) < (visitedTwice ? 1 : 2);
        }

        private List<String> neighbors(String cave) {
            return graph.get(cave).get();
        }

        public int countPaths(String current, List<String> previous) {
            if (current.equals("end")) return 1;
            var visited = previous.append(current);
            return neighbors(current)
                    .filter(cave -> canVisit(cave, visited))
                    .map(n -> countPaths(n, visited))
                    .sum()
                    .intValue();
        }

        public int countPaths2(String current, List<String> previous, boolean visitedTwice) {
            if (current.equals("end")) return 1;
            var visited = previous.append(current);
            var hasVisitedTwiceNow = visitedTwice || (StringOps.isLowerCase(current) && previous.contains(current));
            return neighbors(current)
                    .filter(cave -> canVisit(cave, visited, hasVisitedTwiceNow))
                    .map(n -> countPaths2(n, visited, hasVisitedTwiceNow))
                    .sum()
                    .intValue();
        }

        @Override
        public String toString() {
            return graph.toString();
        }
    }

    @Override
    public Integer partOne(Input input) {
        var caves = caves(input);
        return caves.countPaths("start", List.empty());
    }

    @Override
    public Integer partTwo(Input input) {
        var caves = caves(input);
        return caves.countPaths2("start", List.empty(), false);
    }

    private Caves caves(Input input) {
        return new Caves(input.asStream().map(s -> {
                    var parts = s.split("-");
                    return new Tuple2<>(parts[0], parts[1]);
                })
                .flatMap(t -> List.of(t, new Tuple2<>(t._2, t._1)))
                .filter(t -> !"start".equals(t._2))
                .groupBy(t -> t._1)
                .map(t -> new Tuple2<>(t._1, t._2.map(Tuple2::_2).toList()))
                .toMap(Function.identity()));
    }

}
