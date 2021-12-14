package year2021.day13;

import common.input.Input;
import common.input.InputGroup;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day13Test {

    @Test
    void part1_acceptance() {
        var input = mock(Input.class);
        when(input.asGroups()).thenReturn(asList(
                InputGroup.of("6,10",
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
                InputGroup.of("fold along y=7")
        ));
        var solution = new Day13();
        assertThat(solution.partOne(input)).isEqualTo(17);
    }
}
