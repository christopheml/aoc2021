package common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringOpsTest {


    @Test
    void asListWithVariableSpaces() {
        assertThat(StringOps.asList("10  3 42", "\\s+", Integer::valueOf)).containsExactly(10, 3, 42);
    }
}
