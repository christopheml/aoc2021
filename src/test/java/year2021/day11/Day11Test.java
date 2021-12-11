package year2021.day11;

import common.runners.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day11Test {


    @Test
    void name() {
        var input = mock(Input.class);
        when(input.asList()).thenReturn(List.of("5483143223",
                "2745854711",
                "5264556173",
                "6141336146",
                "6357385478",
                "4167524645",
                "2176841721",
                "6882881134",
                "4846848554",
                "5283751526"));
        var solution = new Day11();
        solution.partOne(input);
    }
}
