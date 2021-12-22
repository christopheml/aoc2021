package year2021.day08;

import common.input.Input;
import io.vavr.collection.Stream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day08Test {

    @Test
    void part2_acceptance() {
        var input = mock(Input.class);
        when(input.asStream()).thenReturn(Stream.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"));
        var solution = new Day08();
        assertThat(solution.partTwo(input)).isEqualTo(5353);
    }

}
