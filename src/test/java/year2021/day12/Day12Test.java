package year2021.day12;

import common.input.Input;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day12Test {

    @Test
    void part1_simple_case() {
        var input = mock(Input.class);
        when(input.asStreamOfLines()).thenReturn(Stream.of("start-A",
                "start-b",
                "A-c",
                "A-b",
                "b-d",
                "A-end",
                "b-end"));
        assertThat(new Day12().partOne(input)).isEqualTo(10);
    }

    @Test
    void part2_simple_case() {
        var input = mock(Input.class);
        when(input.asStreamOfLines()).thenReturn(Stream.of("start-A",
                "start-b",
                "A-c",
                "A-b",
                "b-d",
                "A-end",
                "b-end"));
        assertThat(new Day12().partTwo(input)).isEqualTo(36);
    }

    @Test
    void part2_bigger_case() {
        var input = mock(Input.class);
        when(input.asStreamOfLines()).thenReturn(Stream.of("dc-end",
                "HN-start",
                "start-kj",
                "dc-start",
                "dc-HN",
                "LN-dc",
                "HN-end",
                "kj-sa",
                "kj-HN",
                "kj-dc"));
        assertThat(new Day12().partTwo(input)).isEqualTo(103);
    }
}
