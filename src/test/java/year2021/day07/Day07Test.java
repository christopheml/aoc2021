package year2021.day07;

import common.input.Input;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day07Test {

    @Test
    void part2AcceptanceTest() {
        var input = mock(Input.class);
        when(input.asSeparatedIntegers()).thenReturn(List.of(16,1,2,0,4,2,7,1,2,14));
        var day07 = new Day07();
        assertThat(day07.partTwo(input)).isEqualTo(168);
    }

}
