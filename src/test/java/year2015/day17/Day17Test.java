package year2015.day17;

import common.runners.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day17Test {

    @Test
    void part1_acceptance() {
        var input = mock(Input.class);
        when(input.asList(any())).thenReturn(List.of(20, 15, 10, 5, 5));
        var solution = new Day17();
        solution.target = 25;
        assertThat(solution.partOne(input)).isEqualTo(4);
    }

}
