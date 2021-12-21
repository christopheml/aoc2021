package year2021.day14;

import common.input.Input;
import common.input.InputGroup;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day14Test {

    @Test
    void part1_acceptance() {
        var input = mock(Input.class);
        when(input.asGroups()).thenReturn(List.of(
                InputGroup.of("NNCB"),
                InputGroup.of("CH -> B",
                        "HH -> N",
                        "CB -> H",
                        "NH -> C",
                        "HB -> C",
                        "HC -> B",
                        "HN -> C",
                        "NN -> C",
                        "BH -> H",
                        "NC -> B",
                        "NB -> B",
                        "BN -> B",
                        "BB -> N",
                        "BC -> B",
                        "CC -> N",
                        "CN -> C")
        ));
        var solution = new Day14();
        assertThat(solution.partOne(input)).isEqualTo(1588);
    }

}
