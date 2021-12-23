package common;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Set;
import io.vavr.collection.Traversable;

public class WeightedGraph<T> {

    private final Set<Tuple3<T, T, Integer>> connections;

    private WeightedGraph(Set<Tuple3<T, T, Integer>> connections) {
        this.connections = connections;
    }

    public Set<T> nodes() {
        return connections.map(t -> t._1);
    }

    public Set<T> neighbors(T node) {
        return connections.filter(t -> t._1.equals(node)).map(Tuple3::_2);
    }

    public int cost(T from, T to) {
        return connections.find(t -> t._1.equals(from) && t._2.equals(to))
                .map(Tuple3::_3)
                .getOrElseThrow(IllegalArgumentException::new);
    }

    public int cost(Seq<T> traversal) {
        return traversal.sliding(2)
                .map(step -> cost(step.get(0), step.get(1)))
                .sum()
                .intValue();
    }

    public List<List<T>> traversals() {
        return nodes().toList().permutations();
    }

    public static <T> WeightedGraph<T> oriented(Traversable<Tuple3<T, T, Integer>> connections) {
        return new WeightedGraph<>(connections.toSet());
    }

    public static <T> WeightedGraph<T> nonOriented(Traversable<Tuple3<T, T, Integer>> connections) {
        return new WeightedGraph<>(connections.flatMap(t -> List.of(t, Tuple.of(t._2, t._1, t._3))).toSet());
    }

}
