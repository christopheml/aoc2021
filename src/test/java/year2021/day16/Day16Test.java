package year2021.day16;

import common.input.Input;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Day16Test {

    @ParameterizedTest
    @CsvSource({"C200B40A82,3", "04005AC33890,54", "CE00C43D881120,9", "D8005AC2A8F0,1", "F600BC2D8F,0",
            "9C005AC2F8F0,0", "9C0141080250320F1802104A08,1"})
    void simple_sum(String word, String value) {
        var input = mock(Input.class);
        when(input.asOneLine()).thenReturn(word);
        assertThat(new Day16().partTwo(input)).isEqualTo(Long.parseLong(value));
    }

}
