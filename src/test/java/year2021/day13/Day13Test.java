package year2021.day13;

import common.runners.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day13Test {

    @Test
    void part1_acceptance() {
        var input = mock(Input.class);
        when(input.asGroups()).thenReturn(List.of(
                List.of("6,10",
                        "0,14",
                        "9,10",
                        "0,3",
                        "10,4",
                        "4,11",
                        "6,0",
                        "6,12",
                        "4,1",
                        "0,13",
                        "10,12",
                        "3,4",
                        "3,0",
                        "8,4",
                        "1,10",
                        "2,14",
                        "8,10",
                        "9,0"),
                List.of("fold along y=7")
        ));
        var solution = new Day13();
        assertThat(solution.partOne(input)).isEqualTo(17);
    }
}
