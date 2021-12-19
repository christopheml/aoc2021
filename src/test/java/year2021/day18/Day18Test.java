package year2021.day18;

import common.input.Input;
import common.tree.BinaryTreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day18Test {

    @ParameterizedTest
    @ValueSource(strings = {"[1,2]",
            "[[1,2],3]",
            "[9,[8,7]]",
            "[[1,9],[8,5]]",
            "[[[[1,2],[3,4]],[[5,6],[7,8]]],9]",
            "[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]",
            "[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]"})
    void parseSnailfish(String number) {
        var solution = new Day18();
        assertThat(solution.parseSnailfish(number).toString()).isEqualTo(number);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "[[[[[9,8],1],2],3],4];[[[[0,9],2],3],4]",
            "[7,[6,[5,[4,[3,2]]]]];[7,[6,[5,[7,0]]]]",
            "[[6,[5,[4,[3,2]]]],1];[[6,[5,[7,0]]],3]",
            "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]];[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]",
            "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]];[[3,[2,[8,0]]],[9,[5,[7,0]]]]"
    })
    void explode(String original, String result) {
        var solution = new Day18();
        var number = solution.parseSnailfish(original);
        assertThat(solution.explode((BinaryTreeNode<Integer>) number)).isTrue();
        assertThat(number.toString()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "[[[[4,3],4],4],[7,[[8,4],9]]];[1,1];[[[[0,7],4],[[7,8],[6,0]]],[8,1]]",
            "[[[[1,1],[2,2]],[3,3]],[4,4]];[5,5];[[[[3,0],[5,3]],[4,4]],[5,5]]",
            "[[[[3,0],[5,3]],[4,4]],[5,5]];[6,6];[[[[5,0],[7,4]],[5,5]],[6,6]]",
            "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]];[7,[[[3,7],[4,3]],[[6,3],[8,8]]]];[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]",
            "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]];[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]];[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]",
            "[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]];[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]];[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]",
            "[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]];[7,[5,[[3,8],[1,4]]]];[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]",
            "[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]];[[2,[2,2]],[8,[8,1]]];[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]"
    })
    void add(String a, String b, String expected) {
        var solution = new Day18();
        var result = solution.add(solution.parseSnailfish(a), solution.parseSnailfish(b));
        assertThat(result.toString()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "[[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]],[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]];[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]",
    })
    void reduce(String original, String result) {
        var solution = new Day18();
        var number = solution.parseSnailfish(original);
        solution.reduce((BinaryTreeNode<Integer>) number);
        assertThat(number.toString()).isEqualTo(result);
    }

    @Test
    void part1_acceptance() {
        var input = mock(Input.class);
        when(input.asList()).thenReturn(List.of("[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
                "[[[5,[2,8]],4],[5,[[9,9],0]]]",
                "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
                "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
                "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
                "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
                "[[[[5,4],[7,7]],8],[[8,3],8]]",
                "[[9,3],[[9,9],[6,[4,9]]]]",
                "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
                "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]"));
        var solution = new Day18();
        assertThat(solution.partOne(input)).isEqualTo(4140);
    }


    @Test
    void scratch() {
        var solution = new Day18();

    }
}
