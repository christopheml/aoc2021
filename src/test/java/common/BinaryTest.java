package common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;

class BinaryTest {

    @ParameterizedTest
    @CsvSource({"0000,0", "1111,15", "0101,5"})
    void fromStringRepresentation(String input, String expected) {
        assertThat(Binary.parse(input)).isEqualTo(parseInt(expected));
    }

    @ParameterizedTest
    @CsvSource({"0,0", "15,1111", "5,101"})
    void toStringRepresentation(String input, String expected) {
        assertThat(Binary.asString(parseInt(input))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"0,0000", "15,1111", "5,0101"})
    void toStringRepresentationWithPadding(String input, String expected) {
        assertThat(Binary.asString(parseInt(input), 4)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"1,0,true", "4,2,true", "6,0,false"})
    void isBitSet(String number, String bit, String expected) {
        assertThat(Binary.isBitSet(parseInt(number), parseInt(bit))).isEqualTo(parseBoolean(expected));
    }

    @Test
    void truncate() {
        assertThat(Binary.truncate(15, 2)).isEqualTo(3);
    }

}
