package year2021.day09;

import common.input.Input;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day09Test {

    @Test
    void part1_acceptance() {
        var input = mock(Input.class);
        when(input.asList()).thenReturn(List.of("2199943210", "3987894921", "9856789892", "8767896789", "9899965678"));
        var solution = new Day09();
        assertThat(solution.partOne(input)).isEqualTo(15);
    }

    @Test
    void part2_acceptance() {
        var input = mock(Input.class);
        when(input.asList()).thenReturn(List.of("2199943210", "3987894921", "9856789892", "8767896789", "9899965678"));
        var solution = new Day09();
        assertThat(solution.partTwo(input)).isEqualTo(1134);
    }

}
