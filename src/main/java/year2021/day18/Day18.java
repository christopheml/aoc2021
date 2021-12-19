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

        while (!nodes.isEmpty()) {
            var top = nodes.pop();
            var node = top._1;
            var depth = top._2;

            if (depth == 4) {
                addLeft(node);
                addRight(node);
                node.parent().replace(node, new BinaryTreeValue<>(0));
                return true;
            }
            if (node.right() instanceof BinaryTreeNode<Integer> right) nodes.push(Tuple.of(right, depth + 1));
            if (node.left() instanceof BinaryTreeNode<Integer> left) nodes.push(Tuple.of(left, depth + 1));
        }
        return false;
    }

    private void addLeft(BinaryTreeNode<Integer> exploded) {
        BinaryTreeNode<Integer> node = findFirstParent(exploded, BinaryTreeNode::isRight);
        if (node != null) {
            var closestLeft = findOutermost(node.parent().left(), BinaryTreeNode::right);
            closestLeft.setValue(closestLeft.value() + ((BinaryTreeValue<Integer>) exploded.left()).value());
        }
    }

    private void addRight(BinaryTreeNode<Integer> exploded) {
        BinaryTreeNode<Integer> node = findFirstParent(exploded, BinaryTreeNode::isLeft);
        if (node != null) {
            var closestRight = findOutermost(node.parent().right(), BinaryTreeNode::left);
            closestRight.setValue(closestRight.value() + ((BinaryTreeValue<Integer>) exploded.right()).value());
        }
    }

    private BinaryTreeValue<Integer> findOutermost(BinaryTree<Integer> root,
                                                   Function<BinaryTreeNode<Integer>, BinaryTree<Integer>> direction) {
        BinaryTree<Integer> subtree = root;
        while (!(subtree instanceof BinaryTreeValue<Integer> outermost)) {
            subtree = direction.apply((BinaryTreeNode<Integer>) subtree);
        }
        return outermost;
    }

    private BinaryTreeNode<Integer> findFirstParent(BinaryTreeNode<Integer> exploded, Predicate<BinaryTreeNode<Integer>> predicate) {
        var node = exploded;
        while (node != null && !predicate.test(node)) node = node.parent();
        return node;
    }

    public boolean split(BinaryTreeNode<Integer> number) {
        return findFirstValue(number, v -> v > 9)
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


    private Option<BinaryTreeValue<Integer>> findFirstValue(BinaryTreeNode<Integer> root, Predicate<Integer> predicate) {
        Deque<BinaryTree<Integer>> nodes = new ArrayDeque<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            var node = nodes.pop();
            if (node instanceof BinaryTreeValue<Integer> value) {
                if (predicate.test(value.value())) return Option.some(value);
            } else if (node instanceof BinaryTreeNode<Integer> next) {
                nodes.push(next.right());
                nodes.push(next.left());
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

    public int magnitude(BinaryTree<Integer> number) {
        return switch (number) {
            case BinaryTreeValue<Integer> value -> value.value();
            case BinaryTreeNode<Integer> node -> magnitude(node.left()) * 3 + magnitude(node.right()) * 2;
        };
    }

    @Override
    public Integer partOne(Input input) {
        var result = List.ofAll(input.asList()).map(this::parseSnailfish).reduceLeft(this::add);
        return magnitude(result);
    }

    @Override
    public Integer partTwo(Input input) {
        return List.ofAll(input.asList()).map(this::parseSnailfish).combinations(2)
                .flatMap(pair -> List.of(
                        Tuple.of(pair.head().copy(), pair.last().copy()),
                        Tuple.of(pair.last().copy(), pair.head().copy())))
                .map(pair -> pair.apply(this::add))
                .map(this::magnitude)
                .max().getOrElseThrow(IllegalStateException::new);
    }
}
