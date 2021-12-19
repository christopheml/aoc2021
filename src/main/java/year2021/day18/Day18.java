package year2021.day18;

import common.input.Input;
import common.runners.Solution;
import common.tree.BinaryTree;
import common.tree.BinaryTreeNode;
import common.tree.BinaryTreeValue;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Option;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;
import java.util.function.Predicate;

public class Day18 extends Solution<Integer> {
    public Day18() {
        super(2021, 18);
    }

    public boolean explode(BinaryTreeNode<Integer> number) {
        Deque<Tuple2<BinaryTreeNode<Integer>, Integer>> nodes = new ArrayDeque<>();
        nodes.push(Tuple.of(number, 0));

        BinaryTreeNode<Integer> explodedNode = null;
        while (!nodes.isEmpty()) {
            var top = nodes.pop();
            var node = top._1;
            var depth = top._2;

            if (depth == 4 && explodedNode == null) {
                System.out.println("Exploded node : " + node);
                explodedNode = node;
            }
            if (node.right() instanceof BinaryTreeNode<Integer> right) nodes.push(Tuple.of(right, depth + 1));
            if (node.left() instanceof BinaryTreeNode<Integer> left) nodes.push(Tuple.of(left, depth + 1));
        }
        if (explodedNode != null) {
            var closest = findClosest(explodedNode);
            explode(explodedNode, closest._1, closest._2);
        }
        return explodedNode != null;
    }

    private void explode(BinaryTreeNode<Integer> exploded, Option<BinaryTreeValue<Integer>> closestLeft, Option<BinaryTreeValue<Integer>> closestRight) {
        System.out.println("closest left = " + closestLeft + " closest right = " + closestRight);
        closestLeft.forEach(l -> l.setValue(l.value() + ((BinaryTreeValue<Integer>) exploded.left()).value()));
        closestRight.forEach(r -> r.setValue(r.value() + ((BinaryTreeValue<Integer>) exploded.right()).value()));
        exploded.parent().replace(exploded, new BinaryTreeValue<>(0));
    }

    public boolean split(BinaryTreeNode<Integer> number) {
        return findFirstValue(number, v -> v > 10)
                .map(node -> {
                    var value = node.value();
                    var left = (int) (Math.floor(value / 2.0f));
                    var right = (int) (Math.ceil(value / 2.0f));
                    node.parent()
                            .replace(node, new BinaryTreeNode<>(
                                    new BinaryTreeValue<>(left),
                                    new BinaryTreeValue<>(right)));
                    return true;
                }).getOrElse(false);
    }

    public Tuple2<Option<BinaryTreeValue<Integer>>, Option<BinaryTreeValue<Integer>>> findClosest(BinaryTreeNode<Integer> origin) {
        var root = origin.root();
        Deque<BinaryTreeNode<Integer>> nodes = new ArrayDeque<>();
        var values = List.<BinaryTree<Integer>>empty();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            var node = nodes.pop();
            values = values.append(node.left());
            values = values.append(node);
            values = values.append(node.right());

            if (node.right() instanceof BinaryTreeNode<Integer> next) nodes.push(next);
            if (node.left() instanceof BinaryTreeNode<Integer> next) nodes.push(next);
        }

        var originPosition = values.indexOf(origin);

        return Tuple.of(
                values.subSequence(0, originPosition - 2)
                        .findLast(n -> n instanceof BinaryTreeValue<Integer>)
                        .map(node -> (BinaryTreeValue<Integer>) node),
                values.subSequence(originPosition + 2)
                        .find(n1 -> n1 instanceof BinaryTreeValue<Integer>)
                        .map(node -> (BinaryTreeValue<Integer>) node)
        );
    }


    public Option<BinaryTreeValue<Integer>> findClosest(BinaryTreeNode<Integer> origin,
                                                        Function<BinaryTreeNode<Integer>, BinaryTree<Integer>> direction) {
        var node = origin;
        BinaryTreeNode<Integer> previous;
        while (node.parent() != null) {
            previous = node;
            node = node.parent();
            var next = direction.apply(node);
            if (next instanceof BinaryTreeValue<Integer> value) {
                return Option.some(value);
            } else if (next instanceof BinaryTreeNode<Integer> subTree && subTree != previous) {
                return findFirstValue(subTree, i -> true);
            }
        }
        return Option.none();
    }

    public Option<BinaryTreeValue<Integer>> findFirstValue(BinaryTreeNode<Integer> root, Predicate<Integer> predicate) {
        Deque<BinaryTreeNode<Integer>> nodes = new ArrayDeque<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            var node = nodes.pop();
            if (node.left() instanceof BinaryTreeValue<Integer> value) {
                if (predicate.test(value.value())) return Option.some(value);
            } else {
                nodes.push((BinaryTreeNode<Integer>) node.left());
            }
            if (node.right() instanceof BinaryTreeValue<Integer> value) {
                if (predicate.test(value.value())) return Option.some(value);
            } else {
                nodes.push((BinaryTreeNode<Integer>) node.right());
            }
        }
        return Option.none();
    }

    public BinaryTree<Integer> add(BinaryTree<Integer> a, BinaryTree<Integer> b) {
        var result = new BinaryTreeNode<>(a, b);
        reduce(result);
        return result;
    }

    public void reduce(BinaryTreeNode<Integer> result) {
        while (true) {
            System.out.println(result);
            if (explode(result)) continue;
            if (split(result)) continue;
            break;
        }
    }

    public BinaryTree<Integer> parseSnailfish(String numbers) {
        Deque<BinaryTree<Integer>> nodes = new ArrayDeque<>();
        for (int i = 0; i < numbers.length(); i++) {
            var c = numbers.charAt(i);
            if (Character.isDigit(c)) {
                nodes.push(new BinaryTreeValue<>(c - '0'));
            } else if (c == ']') {
                var right = nodes.pop();
                var left = nodes.pop();
                nodes.push(new BinaryTreeNode<>(left, right));
            }
        }
        return nodes.pop();
    }

    @Override
    public Integer partOne(Input input) {
        return null;
    }

    @Override
    public Integer partTwo(Input input) {
        return null;
    }
}
