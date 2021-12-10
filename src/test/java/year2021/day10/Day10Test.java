package year2021.day10;

import common.runners.Input;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day10Test {

    @Test
    void part2_acceptance() {
        var input = mock(Input.class);
        when(input.asStreamOfLines()).thenReturn(
                Stream.of("[({(<(())[]>[[{[]{<()<>>",
                        "[(()[<>])]({[<{<<[]>>(",
                        "{([(<{}[<>[]}>{[]{[(<()>",
                        "(((({<>}<{<{<>}{[]{[]{}",
                        "[[<[([]))<([[{}[[()]]]",
                        "[{[{({}]{}}([{[{{{}}([]",
                        "{<[[]]>}<{[{[{[]{()[[[]",
                        "[<(<(<(<{}))><([]([]()",
                        "<{([([[(<>()){}]>(<<{{",
                        "<{([{{}}[<[[[<>{}]]]>[]]")
        );
        var solution = new Day10();
        assertThat(solution.partTwo(input)).isEqualTo(288957);
    }

}
