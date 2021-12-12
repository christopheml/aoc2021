package common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringOpsTest {

    @Test
    void asListWithVariableSpaces() {
        assertThat(StringOps.asList("10  3 42", "\\s+", Integer::valueOf)).containsExactly(10, 3, 42);
    }

    @Test
    void isUpperCase() {
        assertThat(StringOps.isUpperCase("Hello")).isFalse();
        assertThat(StringOps.isUpperCase("hello")).isFalse();
        assertThat(StringOps.isUpperCase("HELLO")).isTrue();
    }

    @Test
    void isLowerCase() {
        assertThat(StringOps.isLowerCase("Hello")).isFalse();
        assertThat(StringOps.isLowerCase("hello")).isTrue();
        assertThat(StringOps.isLowerCase("HELLO")).isFalse();
    }

}
