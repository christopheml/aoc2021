package year2019.day03;

import common.input.Input;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day03Test {

    @Test
    void part2_acceptance() {
        var input = mock(Input.class);
        when(input.asList()).thenReturn(List.of(
                "R8,U5,L5,D3",
                "U7,R6,D4,L4"
        ));
        var solution = new Day03();
        assertThat(solution.partTwo(input)).isEqualTo(30);
    }

}
